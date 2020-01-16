package com.accenture.flowershop.back.controller;

import com.accenture.flowershop.back.business.service.CustomerOrderService;
import com.accenture.flowershop.back.business.service.UserService;
import com.accenture.flowershop.back.entity.*;
import com.accenture.flowershop.front.dto.CartDto;
import com.accenture.flowershop.front.dto.CustomerOrderDto;
import com.accenture.flowershop.front.dto.UserDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.Objects;

@Controller
public class OrderController {

    private static final Logger logger = LoggerFactory.getLogger(OrderController.class);

    @Autowired
    private CustomerOrderService customerOrderService;

    @Autowired
    private UserService userService;

    @RequestMapping("/order")
    public String createOrder(HttpSession httpSession, Model model) {
        SessionAttributes sessionAttributes = (SessionAttributes) httpSession.getAttribute("sessionAttributes");
        Users user = userService.getUserByUsername(sessionAttributes.getUser().getUserName());
        CustomerOrder customerOrder = customerOrderService.createCustomerAndAddOrder(sessionAttributes.getCart().dtoToEntity(), user.getUserName());
        sessionAttributes.setCart(new CartDto());
        httpSession.setAttribute("sessionAttributes", sessionAttributes);
        model.addAttribute("user", new UserDto().entityToDto(user));
        model.addAttribute("order", new CustomerOrderDto().entityToDto(customerOrder));
        if(Objects.nonNull(user.getDiscount())) {
            BigDecimal discountSumm = calculateDiscountSum(customerOrder, user.getDiscount());
            model.addAttribute("totalDiscount", discountSumm);
        } else {
            model.addAttribute("totalDiscount", customerOrder.getTotal());
        }

        return "/order";
    }

    @RequestMapping("/order/{orderId}")
    public String getOrder(@PathVariable(value = "orderId") String orderId, HttpSession httpSession, Model model) {
        SessionAttributes sessionAttributes = (SessionAttributes) httpSession.getAttribute("sessionAttributes");
        Users user = userService.getUserByUsername(sessionAttributes.getUser().getUserName());
        CustomerOrder customerOrder = customerOrderService.getCustomerOrderById(orderId);
        httpSession.setAttribute("sessionAttributes", sessionAttributes);
        model.addAttribute("user", new UserDto().entityToDto(user));
        model.addAttribute("order", new CustomerOrderDto().entityToDto(customerOrder));
        if(Objects.nonNull(user.getDiscount())) {
            BigDecimal discountSumm = calculateDiscountSum(customerOrder, user.getDiscount());
            model.addAttribute("totalDiscount", discountSumm);
        } else {
            model.addAttribute("totalDiscount", customerOrder.getTotal());
        }

        return "/order";
    }

    @RequestMapping("/order/pay/{orderId}")
    public String payOrder(@PathVariable(value = "orderId") String orderId, Model model, HttpSession httpSession) {
        SessionAttributes sessionAttributes = (SessionAttributes) httpSession.getAttribute("sessionAttributes");
        Users user = userService.getUserByUsername(sessionAttributes.getUser().getUserName());
        CustomerOrder customerOrder = customerOrderService.getCustomerOrderById(orderId);
        BigDecimal newBalance = new BigDecimal(0);
        if(Objects.nonNull(user.getDiscount())) {
            BigDecimal discountSumm = calculateDiscountSum(customerOrder, user.getDiscount());
            newBalance = user.getBalance().subtract(discountSumm);
            user.setDiscount(null);
        } else {
            newBalance = user.getBalance().subtract(customerOrder.getTotal());
        }
        user.setBalance(newBalance);
        customerOrderService.payCustomerOrder(orderId);
        userService.updateUser(user);
        sessionAttributes.setUser(new UserDto().entityToDto(user));
        httpSession.setAttribute("sessionAttributes", sessionAttributes);
        model.addAttribute("payOrderSuccess", "Your order payed successfully");

        return "redirect:/catalog";
    }

    @RequestMapping("/order/cancel/{orderId}")
    public String cancelOrder(@PathVariable(value = "orderId") String orderId, Model model, HttpSession httpSession) {
        customerOrderService.cancelOrder(orderId);
        logger.info("Order "+orderId+" canceled successfully");
        return "redirect:/catalog";
    }

    private BigDecimal calculateDiscountSum(CustomerOrder customerOrder, BigDecimal discount) {
        return customerOrder.getTotal().subtract(customerOrder.getTotal().divide(new BigDecimal(100)).multiply(discount));
    }
}
