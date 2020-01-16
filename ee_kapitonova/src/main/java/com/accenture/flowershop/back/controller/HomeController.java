package com.accenture.flowershop.back.controller;

import com.accenture.flowershop.back.business.service.CartService;
import com.accenture.flowershop.back.business.service.CustomerOrderService;
import com.accenture.flowershop.back.business.service.ProductService;
import com.accenture.flowershop.back.business.service.UserService;
import com.accenture.flowershop.back.entity.*;
import com.accenture.flowershop.back.entity.SessionAttributes;
import com.accenture.flowershop.front.dto.CartDto;
import com.accenture.flowershop.front.dto.ProductDto;
import com.accenture.flowershop.front.dto.UserDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

@Controller
public class HomeController {

    private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

    @Autowired
    private CustomerOrderService customerOrderService;

    @Autowired
    private ProductService productService;

    @Autowired
    private UserService userService;

    @Autowired
    private CartService cartService;

    @RequestMapping(value = {"/", "/catalog"}, method = RequestMethod.GET)
    public ModelAndView main(HttpSession httpSession) {
        logger.info("Start point of app");
        ModelAndView modelAndView = new ModelAndView();
        if (httpSession.getAttribute("sessionAttributes") == null) {
            SessionAttributes sessionAttributes = new SessionAttributes();
            CartDto cart = new CartDto();
            UserDto user = new UserDto();
            sessionAttributes.setUser(user);
            sessionAttributes.setCart(cart);
            httpSession.setAttribute("sessionAttributes", sessionAttributes);
            httpSession.setAttribute("userName", null);
            modelAndView.setViewName("redirect:/login");
        } else if (((SessionAttributes) httpSession.getAttribute("sessionAttributes")).getUser().getUserId() == null) {
            modelAndView.setViewName("redirect:/login");
        } else {
            modelAndView.addObject("products", productService.getProductList().stream().map(p -> new ProductDto().entityToDto(p)).collect(Collectors.toList()));
            modelAndView.setViewName("catalog");
        }
        return modelAndView;
    }

    @RequestMapping(value = "/search")
    public ModelAndView search(@RequestParam(value = "searchParam", required = false) String searchParam,
                               @RequestParam(value = "minParam", required = false) String minParam,
                               @RequestParam(value = "maxParam", required = false) String maxParam,
                               HttpSession httpSession) {
        logger.info("Search param "+searchParam);
        ModelAndView modelAndView = new ModelAndView();
        List<Product> products = new ArrayList<>();

        if (searchParam.isEmpty() && minParam.isEmpty() && maxParam.isEmpty()) {
            modelAndView.addObject("products", productService.getProductList().stream().map(p -> new ProductDto().entityToDto(p)).collect(Collectors.toList()));
        } else {
            products.addAll(productService.searchProducts(searchParam, minParam, maxParam));
        }

        if (products.isEmpty()) {
            modelAndView.addObject("noResults", "The query " + searchParam + " " + minParam + " " + maxParam + " returned no results");
        } else {
            modelAndView.addObject("products", products.stream().map(p -> new ProductDto().entityToDto(p)).collect(Collectors.toList()));
            String responseString = "Results for ";
            responseString += !searchParam.isEmpty() ?  searchParam : "";
            responseString += !minParam.isEmpty() ? " minPrice " + minParam : "";
            responseString += !maxParam.isEmpty() ? " maxPrice " + maxParam : "";
            modelAndView.addObject("results", responseString);
        }

        modelAndView.setViewName("search");
        return modelAndView;
    }

    @RequestMapping(value = "/get_quantity_in_cart", method = RequestMethod.POST)
    @ResponseBody
    public String checkQtyInCart(HttpServletRequest request, HttpSession httpSession) {
        SessionAttributes sessionAttributes = (SessionAttributes) httpSession.getAttribute("sessionAttributes");
        String productId = request.getParameter("productId");
        CartDto cartDto = sessionAttributes.getCart();
        CartItem cartItem = null;
        if (Objects.nonNull(cartDto.getCartItems())) {
            cartItem = cartDto.getCartItems().stream()
                    .filter(item -> item.getProductId().equals(productId))
                    .findFirst().orElse(null);
        }
        return Objects.nonNull(cartItem) ? String.valueOf(cartItem.getQuantity()) : String.valueOf(0);
    }

    @RequestMapping(value = "/buy")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void addCartItem(HttpServletRequest request, HttpSession httpSession) {
        String productId = request.getParameter("productId");
        String productQty = request.getParameter("productQty");
        SessionAttributes sessionAttributes = (SessionAttributes) httpSession.getAttribute("sessionAttributes");
        logger.info("Customer : " + sessionAttributes.getUser().getUserId() + " "+ sessionAttributes.getUser().getUserName());
        Cart cart = cartService.addItemToCart(sessionAttributes.getCart().dtoToEntity(), productId, productQty);
        sessionAttributes.setCart(new CartDto().entityToDto(cart));
        httpSession.setAttribute("sessionAttributes", sessionAttributes);
    }

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public ModelAndView admin() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("orders", customerOrderService.getCustomerOrders());
        modelAndView.addObject("users", userService.getAllUsers());
        modelAndView.setViewName("admin");
        return modelAndView;
    }

    @RequestMapping("/admin/closeOrder/{orderId}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void closeOrder(@PathVariable(value = "orderId") String orderId) {
        customerOrderService.closeCustomerOrder(orderId);
        logger.info("Order "+orderId+" closed successfully");
    }

    @RequestMapping(value = "/admin/addMoney/{userId}", method = RequestMethod.GET)
    public ModelAndView addMoney(@PathVariable(value = "userId") String userId) {
        Users user = userService.getUserById(userId);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("user", user);
        modelAndView.setViewName("addmoney");
        return modelAndView;
    }

    @RequestMapping(value = "/addmoney_process", method = RequestMethod.POST)
    public String addmoney_process(@RequestParam(value = "j_username") String username,
                                @RequestParam(value = "j_summ", required = false) BigDecimal summ) {
        userService.updateUserBalance(username, summ);
        return "redirect:/admin";
    }
}
