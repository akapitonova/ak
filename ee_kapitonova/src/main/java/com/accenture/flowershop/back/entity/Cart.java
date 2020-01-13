package com.accenture.flowershop.back.entity;

import org.springframework.stereotype.Component;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Component
public class Cart implements Serializable {

	private static final long serialVersionUID = 8436097833452420298L;

	@GeneratedValue(strategy = GenerationType.AUTO)
	private String cartId;
	private List<CartItem> cartItems;
	private BigDecimal totalPrice;

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
