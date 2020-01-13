package com.accenture.flowershop.back.controller;

import com.accenture.flowershop.back.entity.*;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;

@Controller
public class CartController {

    @RequestMapping(value = "/cart", method = RequestMethod.GET)
    public ModelAndView cart(HttpSession httpSession) {
        ModelAndView modelAndView = new ModelAndView();
        SessionAttributes sessionAttributes = (SessionAttributes) httpSession.getAttribute("sessionAttributes");
        modelAndView.addObject("cart", sessionAttributes.getCart());
        modelAndView.setViewName("cart");
        return modelAndView;
    }

    @RequestMapping(value = "/cart/removeCartItem/{cartItemId}", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public String removeCartItem(@PathVariable(value = "cartItemId") final String cartItemId, HttpSession httpSession) {
        SessionAttributes sessionAttributes = (SessionAttributes) httpSession.getAttribute("sessionAttributes");
        sessionAttributes.getCart().getCartItems().removeIf(e -> e.getProductId().equals(cartItemId));
        BigDecimal totalPrice = new BigDecimal(0);
        for(CartItem item : sessionAttributes.getCart().getCartItems()){
            BigDecimal m = item.getPrice().multiply(new BigDecimal(item.getQuantity()));
            totalPrice = totalPrice.add(m);
        }
        sessionAttributes.getCart().setTotalPrice(totalPrice);
        httpSession.setAttribute("sessionAttributes", sessionAttributes);
        return "redirect:/cart";
    }

    @RequestMapping(value = "/cart/removeAllItems", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public String removeAllCartItems(HttpSession httpSession) {
        SessionAttributes sessionAttributes = (SessionAttributes) httpSession.getAttribute("sessionAttributes");
        sessionAttributes.getCart().getCartItems().clear();
        BigDecimal totalPrice = new BigDecimal(0);
        for(CartItem item : sessionAttributes.getCart().getCartItems()){
            BigDecimal m = item.getPrice().multiply(new BigDecimal(item.getQuantity()));
            totalPrice = totalPrice.add(m);
        }
        sessionAttributes.getCart().setTotalPrice(totalPrice);
        httpSession.setAttribute("sessionAttributes", sessionAttributes);
        return "redirect:/catalog";
    }
}
