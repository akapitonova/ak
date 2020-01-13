package com.accenture.flowershop.front.dto;

import com.accenture.flowershop.back.entity.Users;

import java.io.Serializable;
import java.math.BigDecimal;

public class UserDto implements Serializable {
    private static final long serialVersionUID = -9135693352701600497L;

    private String userId;
    private String userName;
    private String firstName;
    private String lastName;
    private String customerPhone;
    private String shippingAddress;
    private BigDecimal balance;
    private BigDecimal discount;

    public UserDto entityToDto(Users users) {
        this.userId = users.getId().toString();
        this.userName = users.getUserName();
        this.firstName = users.getFirstName();
        this.lastName = users.getLastName();
        this.customerPhone = users.getCustomerPhone();
        this.shippingAddress = users.getShippingAddress();
        this.balance = users.getBalance();
        this.discount = users.getDiscount();

        return this;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    public String getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }


}
