package com.accenture.flowershop.back.controller;

import com.accenture.flowershop.back.business.service.CustomerOrderService;
import com.accenture.flowershop.back.business.service.UserService;
import com.accenture.flowershop.back.entity.CustomerOrder;
import com.accenture.flowershop.back.entity.SessionAttributes;
import com.accenture.flowershop.back.entity.Users;
import com.accenture.flowershop.front.dto.CartDto;
import com.accenture.flowershop.front.dto.UserDto;
import com.accenture.flowershop.front.enums.Role;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

@Controller
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    UserService userService;

    @Autowired
    CustomerOrderService customerOrderService;

    @RequestMapping("/login")
    public String login(@RequestParam(value = "error", required = false) String error,
                        @RequestParam(value = "logout", required = false) String logout, Model model) {
        if (error != null)
            model.addAttribute("error", "Invalid username and Password");
        if (logout != null)
            model.addAttribute("logout", "You have logged out successfully");
        return "login";
    }

    @RequestMapping(value = "/login_process", method = RequestMethod.POST)
    public String login_process(@RequestParam(value = "j_username", required = false) String username,
                                @RequestParam(value = "j_password", required = false) String pass,
                                HttpSession httpSession,
                                HttpServletRequest request) {
        Users user = userService.getUserByUsername(username);

        if (user == null || !user.getPassword().equals(pass)) {
            request.setAttribute("error", "Invalid login or password");
            return "login";
        }

        SessionAttributes sessionAttributes = (SessionAttributes) httpSession.getAttribute("sessionAttributes");
        sessionAttributes.setUser(new UserDto().entityToDto(user));
        httpSession.setAttribute("sessionAttributes", sessionAttributes);
        httpSession.setAttribute("userName", user.getUserName());
        logger.info("User "+username+" login successfully");

        return user.getRole().equals(Role.ADMIN) ? "redirect:/admin" : "redirect:/catalog";
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(HttpSession httpSession, HttpServletRequest request) {
        SessionAttributes sessionAttributes = (SessionAttributes) httpSession.getAttribute("sessionAttributes");
        logger.info("User "+sessionAttributes.getUser().getUserId()+" logout process");
        sessionAttributes.setUser(new UserDto());
        sessionAttributes.setCart(new CartDto());
        httpSession.setAttribute("sessionAttributes", sessionAttributes);
        httpSession.removeAttribute("userName");

        request.setAttribute("logout", "You log out.");

        return "login";
    }

    @RequestMapping(value = "/register")
    public ModelAndView getRegistrationForm() {
        ModelAndView modelAndView = new ModelAndView();
        return new ModelAndView("register", "user", new Users());
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String registerUser(@Valid @ModelAttribute(value = "user") Users user, Model model,
                                   BindingResult result) {
        if (result.hasErrors())
            return "redirect:/register";
        userService.addUser(user);
        logger.info("Added user " + user.getId() + " " + user.getUserName());
        model.addAttribute("registrationSuccess", "Registered Successfully. Login using username and password");
        return "redirect:/login";
    }

    @RequestMapping(value = "/check_user", method = RequestMethod.POST)
    @ResponseBody
    public String checkUser(@RequestParam(value = "username") String username) {
        Users user = userService.getUserByUsername(username);
        return user == null ? "null" : "User is already defined";
    }

    @RequestMapping(value = "/userinfo")
    public ModelAndView userInfo(HttpSession httpSession) {
        SessionAttributes sessionAttributes = (SessionAttributes) httpSession.getAttribute("sessionAttributes");
        UserDto user = sessionAttributes.getUser();
        List<CustomerOrder> orders = customerOrderService.getCustomerOrdersForUser(user.getUserId());
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("user", user);
        modelAndView.addObject("orders", orders);
        modelAndView.setViewName("userinfo");
        return modelAndView;
    }

    @RequestMapping(value = "/change_user_info", method = RequestMethod.POST)
    public String changeUserInfo(@Valid @ModelAttribute(value = "user") UserDto userDto, Model model,
                                   BindingResult result, HttpSession httpSession) {
        if (result.hasErrors())
            return "redirect:/userinfo";
        SessionAttributes sessionAttributes = (SessionAttributes) httpSession.getAttribute("sessionAttributes");
        Users user = userService.updateUserInfo(sessionAttributes.getUser().getUserName(), userDto);
        sessionAttributes.setUser(new UserDto().entityToDto(user));
        httpSession.setAttribute("sessionAttributes", sessionAttributes);
        return "redirect:/catalog";
    }
}
