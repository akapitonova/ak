package com.accenture.flowershop.front.dto;

import com.accenture.flowershop.back.entity.Cart;
import com.accenture.flowershop.back.entity.OrderItem;

import java.io.Serializable;
import java.math.BigDecimal;

public class OrderItemDto implements Serializable {
    private static final long serialVersionUID = -3863690753226937225L;

    private String orderItemId;
    private int quantity;
    private BigDecimal price;
    private String productName;
    private String productid;

    public OrderItemDto entityToDto(OrderItem orderItem) {
        this.orderItemId = orderItem.getOrderItemId().toString();
        this.quantity = orderItem.getQuantity();
        this.price = orderItem.getPrice();
        this.productName = orderItem.getProductName();
        this.productid = orderItem.getProductid();

        return this;
    }

    public OrderItem dtoToEntity() {
        OrderItem orderItem = new OrderItem();

        return orderItem;
    }

    public String getOrderItemId() {
        return orderItemId;
    }

    public void setOrderItemId(String orderItemId) {
        this.orderItemId = orderItemId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductid() {
        return productid;
    }

    public void setProductid(String productid) {
        this.productid = productid;
    }


}
