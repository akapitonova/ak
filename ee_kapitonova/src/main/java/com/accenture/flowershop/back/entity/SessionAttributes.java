package com.accenture.flowershop.back.entity;

import com.accenture.flowershop.front.dto.CartDto;
import com.accenture.flowershop.front.dto.UserDto;
import org.springframework.stereotype.Component;

@Component
public class SessionAttributes {
    private CartDto cart;
    private UserDto user;

    public CartDto getCart() {
        return cart;
    }

    public void setCart(CartDto cart) {
        this.cart = cart;
    }

    public UserDto getUser() {
        return user;
    }

    public void setUser(UserDto user) {
        this.user = user;
    }
}
