package com.accenture.flowershop.front.dto;

import com.accenture.flowershop.back.entity.Cart;
import com.accenture.flowershop.back.entity.CartItem;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

public class CartDto implements Serializable {
    private static final long serialVersionUID = -3944873383940140866L;

    private String cartId;
    private List<CartItem> cartItems;
    private BigDecimal totalPrice;

    public CartDto entityToDto(Cart cart) {
        this.cartId = cart.getCartId();
        this.cartItems = cart.getCartItems();
        this.totalPrice = cart.getTotalPrice();

        return this;
    }

    public Cart dtoToEntity() {
        Cart cart = new Cart();
        cart.setCartId(this.cartId);
        cart.setCartItems(this.cartItems);
        cart.setTotalPrice(this.totalPrice);

        return cart;
    }

    public String getCartId() {
        return cartId;
    }

    public void setCartId(String cartId) {
        this.cartId = cartId;
    }

    public List<CartItem> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<CartItem> cartItems) {
        this.cartItems = cartItems;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

}
