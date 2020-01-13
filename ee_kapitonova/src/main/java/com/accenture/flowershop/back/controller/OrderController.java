package com.accenture.flowershop.back.controller;

import com.accenture.flowershop.back.business.service.CustomerOrderService;
import com.accenture.flowershop.back.business.service.UserService;
import com.accenture.flowershop.back.entity.*;
import com.accenture.flowershop.front.dto.CartDto;
import com.accenture.flowershop.front.enums.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Controller
public class OrderController {

    @Autowired
    private CustomerOrderService customerOrderService;

    @Autowired
    private UserService userService;

    @RequestMapping("/order")
    public String createOrder(HttpSession httpSession, Model model) {
        SessionAttributes sessionAttributes = (SessionAttributes) httpSession.getAttribute("sessionAttributes");
        Users user = userService.getUserByUsername(sessionAttributes.getUser().getUserName());
        CustomerOrder customerOrder = new CustomerOrder();
        customerOrder.setUser(user);
        customerOrder.setStatus(Status.CREATED);
        customerOrder.setOpenDate(new Date());
        Cart cart = sessionAttributes.getCart().dtoToEntity();
        customerOrder.setTotal(cart.getTotalPrice());
        List<OrderItem> orderItems = new ArrayList<OrderItem>();
        for (CartItem cartItem : cart.getCartItems()) {
            OrderItem orderItem = new OrderItem();
            orderItem.setProductid(cartItem.getProductId());
            orderItem.setProductName(cartItem.getProductName());
            orderItem.setPrice(cartItem.getPrice());
            orderItem.setQuantity(cartItem.getQuantity());
            orderItem.setCustomerOrder(customerOrder);
            orderItems.add(orderItem);
        }
        customerOrder.setOrderItems(orderItems);
        customerOrderService.addCustomerOrder(customerOrder);
        sessionAttributes.setCart(new CartDto());

        httpSession.setAttribute("sessionAttributes", sessionAttributes);
        model.addAttribute("user", user);
        model.addAttribute("order", customerOrder);

        return "/order";
    }

    @RequestMapping("/order/{orderId}")
    public String getOrder(@PathVariable(value = "orderId") String orderId, HttpSession httpSession, Model model) {
        SessionAttributes sessionAttributes = (SessionAttributes) httpSession.getAttribute("sessionAttributes");
        Users user = userService.getUserByUsername(sessionAttributes.getUser().getUserName());
        CustomerOrder customerOrder = customerOrderService.getCustomerOrderById(orderId);
        httpSession.setAttribute("sessionAttributes", sessionAttributes);
        model.addAttribute("user", user);
        model.addAttribute("order", customerOrder);

        return "/order";
    }

    @RequestMapping("/order/pay/{orderId}")
    public String payOrder(@PathVariable(value = "orderId") String orderId, Model model, HttpSession httpSession) {
        SessionAttributes sessionAttributes = (SessionAttributes) httpSession.getAttribute("sessionAttributes");
        Users user = userService.getUserByUsername(sessionAttributes.getUser().getUserName());
        CustomerOrder customerOrder = customerOrderService.getCustomerOrderById(orderId);
        if (user.getBalance().compareTo(customerOrder.getTotal()) == -1) {
            model.addAttribute("notHaveFounds", "You do not have enough funds in your account!");
            return "redirect:/order/"+orderId;
        }
        BigDecimal newBalance = new BigDecimal(0);
        if(Objects.nonNull(user.getDiscount())) {
            BigDecimal discountSumm = customerOrder.getTotal().divide(new BigDecimal(100)).pow(user.getDiscount().intValue());
            newBalance = user.getBalance().subtract(discountSumm);
        } else {
            newBalance = user.getBalance().subtract(customerOrder.getTotal());
        }
        user.setBalance(newBalance);
        user.setDiscount(null);
        customerOrderService.payCustomerOrder(orderId);
        userService.updateUser(user);
        model.addAttribute("payOrderSuccess", "Your order payed successfully");
        return "redirect:/catalog";
    }

    @RequestMapping("/order/cancel/{orderId}")
    public String cancelOrder(@PathVariable(value = "orderId") String orderId, Model model, HttpSession httpSession) {
        customerOrderService.cancelOrder(orderId);

        return "redirect:/catalog";
    }
}
