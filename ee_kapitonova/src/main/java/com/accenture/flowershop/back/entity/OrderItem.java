package com.accenture.flowershop.back.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "orderitem")
public class OrderItem implements Serializable {
    private static final long serialVersionUID = 5414968505955957016L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "orderitemid")
    private Long orderItemId;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "productname")
    private String productName;

    @Column(name = "productid")
    private String productid;

    @ManyToOne
    @JoinColumn(name = "customerOrderId")
    private CustomerOrder customerOrder;

    public Long getId() {
        return orderItemId;
    }

    public void setId(Long orderItemId) {
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

    public CustomerOrder getCustomerOrder() {
        return customerOrder;
    }

    public void setCustomerOrder(CustomerOrder customerOrder) {
        this.customerOrder = customerOrder;
    }

    public Long getOrderItemId() {
        return orderItemId;
    }

    public void setOrderItemId(Long orderItemId) {
        this.orderItemId = orderItemId;
    }

    public String getProductid() {
        return productid;
    }

    public void setProductid(String productid) {
        this.productid = productid;
    }
}
