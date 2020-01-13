package com.accenture.flowershop.back.controller;

import com.accenture.flowershop.back.business.service.CustomerOrderService;
import com.accenture.flowershop.back.business.service.ProductService;
import com.accenture.flowershop.back.business.service.UserService;
import com.accenture.flowershop.back.entity.*;
import com.accenture.flowershop.back.entity.SessionAttributes;
import com.accenture.flowershop.front.dto.CartDto;
import com.accenture.flowershop.front.dto.UserDto;
import com.accenture.flowershop.front.enums.Role;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Controller
public class HomeController {

    private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

    @Autowired
    private CustomerOrderService customerOrderService;

    @Autowired
    private ProductService productService;

    @Autowired
    private UserService userService;

    @RequestMapping(value = {"/", "/catalog"}, method = RequestMethod.GET)
    public ModelAndView main(HttpSession httpSession) {
        ModelAndView modelAndView = new ModelAndView();
        if (httpSession.getAttribute("sessionAttributes") == null) {
            SessionAttributes sessionAttributes = new SessionAttributes();
            CartDto cart = new CartDto();
            UserDto user = new UserDto();
            sessionAttributes.setUser(user);
            sessionAttributes.setCart(cart);
            httpSession.setAttribute("sessionAttributes", sessionAttributes);
            httpSession.setAttribute("userName", null);
        }
        modelAndView.addObject("products", productService.getProductList());
        modelAndView.setViewName("catalog");
        return modelAndView;
    }

    @RequestMapping(value = "buy/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void addCartItem(@PathVariable(value = "id") String productId, HttpSession httpSession) {
        Product product = productService.findProduct(productId);
        SessionAttributes sessionAttributes = (SessionAttributes) httpSession.getAttribute("sessionAttributes");
        Cart cart = sessionAttributes.getCart().dtoToEntity();
        if(Objects.isNull(cart)) {
            cart = new Cart();
        }
        logger.info("Customer : " + sessionAttributes.getUser().getUserId() + " "+ sessionAttributes.getUser().getUserName());
        logger.info("Cart : "+cart.getCartId());
        List<CartItem> cartItems = new ArrayList<CartItem>();
        CartItem cartItem = new CartItem();
        if (Objects.nonNull(cart.getCartItems())) {
            cartItem = cart.getCartItems().stream()
                    .filter(item -> item.getProductId().equals(product.getId()))
                    .findFirst().orElse(null);
            cartItems = cart.getCartItems();
        }
        if (Objects.nonNull(cartItem) && Objects.nonNull(cartItem.getProductId())) {
            cartItem.setQuantity(cartItem.getQuantity() + 1);
            cart.getCartItems().set(cart.getCartItems().indexOf(cartItem), cartItem);
        } else {
            cartItem = new CartItem();
            Integer qty = 1;
            cartItem.setQuantity(qty);
            cartItem.setProductId(product.getId());
            cartItem.setProductName(product.getName());
            cartItem.setPrice(product.getPrice());
            cartItem.setCart(cart);
            cartItems.add(cartItem);
            cart.setCartItems(cartItems);
        }
        BigDecimal totalPrice = new BigDecimal(0);
        for(CartItem item : cartItems){
            BigDecimal m = item.getPrice().multiply(new BigDecimal(item.getQuantity()));
            totalPrice = totalPrice.add(m);
        }
        cart.setTotalPrice(totalPrice);
        CartDto cartDto = new CartDto();
        sessionAttributes.setCart(cartDto.entityToDto(cart));
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
        Users user = userService.getUserByUsername(username);
        user.setBalance(user.getBalance().add(summ));
        userService.updateUser(user);

        return "redirect:/admin";
    }
}
