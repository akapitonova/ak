package com.accenture.flowershop.back.controller;

import com.accenture.flowershop.back.entity.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;

@Controller
public class CartController {

    private static final Logger logger = LoggerFactory.getLogger(CartController.class);

    @RequestMapping(value = "/cart", method = RequestMethod.GET)
    public ModelAndView cart(HttpSession httpSession) {
        ModelAndView modelAndView = new ModelAndView();
        SessionAttributes sessionAttributes = (SessionAttributes) httpSession.getAttribute("sessionAttributes");
        modelAndView.addObject("cart", sessionAttributes.getCart());
        modelAndView.setViewName("cart");
        return modelAndView;
    }

    @RequestMapping(value = "/cart/removeCartItem", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void removeCartItem(HttpServletRequest request, HttpSession httpSession) {
        String cartItemId = request.getParameter("cartItemId");
        SessionAttributes sessionAttributes = (SessionAttributes) httpSession.getAttribute("sessionAttributes");
        sessionAttributes.getCart().getCartItems().removeIf(e -> e.getProductId().equals(cartItemId));
        double totalPrice = 0.0;
        for(CartItem item : sessionAttributes.getCart().getCartItems()){
            totalPrice += item.getPrice().doubleValue() * item.getQuantity();
        }
        sessionAttributes.getCart().setTotalPrice(new BigDecimal(totalPrice));
        httpSession.setAttribute("sessionAttributes", sessionAttributes);
    }

    @RequestMapping(value = "/cart/removeAllItems", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public String removeAllCartItems(HttpSession httpSession) {
        SessionAttributes sessionAttributes = (SessionAttributes) httpSession.getAttribute("sessionAttributes");
        sessionAttributes.getCart().getCartItems().clear();
        BigDecimal totalPrice = new BigDecimal(0);
        sessionAttributes.getCart().setTotalPrice(totalPrice);
        logger.info("Cart cleared for user "+sessionAttributes.getUser().getUserId());
        httpSession.setAttribute("sessionAttributes", sessionAttributes);
        return "redirect:/catalog";
    }
}
