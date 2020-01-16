package com.accenture.flowershop.back.business.service;

import com.accenture.flowershop.back.entity.Cart;
import com.accenture.flowershop.back.entity.CartItem;
import com.accenture.flowershop.back.entity.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class CartServiceImpl implements CartService {

    private static final Logger logger = LoggerFactory.getLogger(CartServiceImpl.class);

    @Autowired
    private ProductService productService;

    public Cart addItemToCart(Cart cart, String productId, String productQty) {
        Product product = productService.findProduct(productId);
        if(Objects.isNull(cart)) {
            cart = new Cart();
        }
        logger.info("Cart : "+cart.getCartId());
        List<CartItem> cartItems = new ArrayList<CartItem>();
        CartItem cartItem = new CartItem();
        if (Objects.nonNull(cart.getCartItems())) {
            cartItems = cart.getCartItems();
            cartItem = cartItems.stream()
                    .filter(item -> item.getProductId().equals(product.getId()))
                    .findFirst().orElse(null);
        }
        if (Objects.nonNull(cartItem) && Objects.nonNull(cartItem.getProductId())) {
            cartItem.setQuantity(cartItem.getQuantity() + Integer.valueOf(productQty));
            cart.getCartItems().set(cart.getCartItems().indexOf(cartItem), cartItem);
        } else {
            cartItem = new CartItem();
            cartItem.setQuantity(Integer.valueOf(productQty));
            cartItem.setProductId(product.getId());
            cartItem.setProductName(product.getName());
            cartItem.setPrice(product.getPrice());
            cartItem.setCart(cart);
            cartItems.add(cartItem);
            cart.setCartItems(cartItems);
        }
        BigDecimal totalPrice = new BigDecimal(0);
        for(CartItem item : cartItems) {
            totalPrice = totalPrice.add(item.getPrice().multiply(new BigDecimal(item.getQuantity())));
        }
        cart.setTotalPrice(totalPrice);

        return cart;
    }
}
