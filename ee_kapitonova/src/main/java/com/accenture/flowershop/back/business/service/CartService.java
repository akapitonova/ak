package com.accenture.flowershop.back.business.service;

import com.accenture.flowershop.back.entity.Cart;

public interface CartService {
    Cart addItemToCart(Cart cart, String productId, String productQty);
}
