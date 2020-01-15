package com.accenture.flowershop.front.dto;

import com.accenture.flowershop.back.entity.CustomerOrder;
import com.accenture.flowershop.back.entity.OrderItem;
import com.accenture.flowershop.front.enums.Status;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CustomerOrderDto implements Serializable {
    private static final long serialVersionUID = 6905592531845858981L;

    private String customerOrderId;
    private Status status;
    private BigDecimal total;
    private Date openDate;
    private Date closeDate;
    private UserDto user;
    private List<OrderItemDto> orderItems;

    public CustomerOrderDto entityToDto(CustomerOrder customerOrder) {
        this.customerOrderId = customerOrder.getId().toString();
        this.status = customerOrder.getStatus();
        this.openDate = customerOrder.getOpenDate();
        this.closeDate = customerOrder.getCloseDate();
        this.user = new UserDto().entityToDto(customerOrder.getUser());
        orderItems = new ArrayList<>();
        for(OrderItem orderItem : customerOrder.getOrderItems()) {
            orderItems.add(new OrderItemDto().entityToDto(orderItem));
        }

        return this;
    }

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

    public UserDto getUser() {
        return user;
    }

    public void setUser(UserDto user) {
        this.user = user;
    }

    public List<OrderItemDto> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItemDto> orderItems) {
        this.orderItems = orderItems;
    }

    public Date getOpenDate() {
        return openDate;
    }

    public void setOpenDate(Date openDate) {
        this.openDate = openDate;
    }

    public Date getCloseDate() {
        return closeDate;
    }

    public void setCloseDate(Date closeDate) {
        this.closeDate = closeDate;
    }


}
