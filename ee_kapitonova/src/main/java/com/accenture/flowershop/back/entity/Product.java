package com.accenture.flowershop.back.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "product")
public class Product implements Serializable {
    private static final long serialVersionUID = -7935825963046377138L;

    @Id
    @Column(name = "productid")
    private String productId;

    @Column(name = "name")
    private String name;

    @Column(name = "photo")
    private String photo;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "storeqty")
    private int storeqty;

    public String getId() {
        return productId;
    }

    public void setId(String productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getStoreqty() {
        return storeqty;
    }

    public void setStoreqty(int storeqty) {
        this.storeqty = storeqty;
    }
}
