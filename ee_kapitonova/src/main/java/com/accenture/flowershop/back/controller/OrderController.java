package com.accenture.flowershop.back.controller;

import com.accenture.flowershop.back.business.service.CustomerOrderService;
import com.accenture.flowershop.back.business.service.UserService;
import com.accenture.flowershop.back.entity.*;
import com.accenture.flowershop.front.dto.CartDto;
import com.accenture.flowershop.front.dto.CustomerOrderDto;
import com.accenture.flowershop.front.dto.UserDto;
import com.accenture.flowershop.front.enums.Status;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
        model.addAttribute("user", new UserDto().entityToDto(user));
        model.addAttribute("order", new CustomerOrderDto().entityToDto(customerOrder));
        if(Objects.nonNull(user.getDiscount())) {
            double discountSumm = customerOrder.getTotal().doubleValue() - customerOrder.getTotal().doubleValue() / 100.0 * user.getDiscount().intValue();
            model.addAttribute("totalDiscount", discountSumm);
        } else {
            model.addAttribute("totalDiscount", customerOrder.getTotal().doubleValue());
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
            double discountSumm = customerOrder.getTotal().doubleValue() - customerOrder.getTotal().doubleValue() / 100.0 * user.getDiscount().intValue();
            model.addAttribute("totalDiscount", discountSumm);
        } else {
            model.addAttribute("totalDiscount", customerOrder.getTotal().doubleValue());
        }

        return "/order";
    }

    @RequestMapping("/order/pay/{orderId}")
    public String payOrder(@PathVariable(value = "orderId") String orderId, Model model, HttpSession httpSession) {
        SessionAttributes sessionAttributes = (SessionAttributes) httpSession.getAttribute("sessionAttributes");
        Users user = userService.getUserByUsername(sessionAttributes.getUser().getUserName());
        CustomerOrder customerOrder = customerOrderService.getCustomerOrderById(orderId);
        double newBalance = 0.0;
        if(Objects.nonNull(user.getDiscount())) {
            double discountSumm = customerOrder.getTotal().doubleValue() - customerOrder.getTotal().doubleValue() / 100.0 * user.getDiscount().intValue();
            newBalance = user.getBalance().doubleValue() - discountSumm;
            user.setDiscount(null);
        } else {
            newBalance = user.getBalance().doubleValue() - customerOrder.getTotal().doubleValue();
        }
        user.setBalance(new BigDecimal(newBalance));
        customerOrderService.payCustomerOrder(orderId);
        userService.updateUser(user);
        model.addAttribute("payOrderSuccess", "Your order payed successfully");

        return "redirect:/catalog";
    }

    @RequestMapping("/order/cancel/{orderId}")
    public String cancelOrder(@PathVariable(value = "orderId") String orderId, Model model, HttpSession httpSession) {
        customerOrderService.cancelOrder(orderId);
        logger.info("Order "+orderId+" canceled successfully");
        return "redirect:/catalog";
    }
}
