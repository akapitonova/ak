package com.accenture.flowershop.front.dto;

import com.accenture.flowershop.back.entity.Users;
import com.accenture.flowershop.front.enums.Status;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

public class CustomerOrderDto implements Serializable {
    private static final long serialVersionUID = 6905592531845858981L;

    private String customerOrderId;
    private Status status;
    private BigDecimal total;
    private Users user;
    private List<OrderItemDto> orderItems;

    public String getCustomerOrderId() {
        return customerOrderId;
    }

    public void setCustomerOrderId(String customerOrderId) {
        this.customerOrderId = customerOrderId;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public List<OrderItemDto> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItemDto> orderItems) {
        this.orderItems = orderItems;
    }


}
