package com.accenture.flowershop.front.dto;

import com.accenture.flowershop.back.entity.Product;

import java.io.Serializable;

public class ProductDto implements Serializable {
    private static final long serialVersionUID = 7573997245512866475L;

    private String productId;
    private String name;
    private String photo;
    private Double price;
    private int storeqty;

    public ProductDto entityToDto(Product product) {
        this.productId = product.getId();
        this.name = product.getName();
        this.photo = product.getPhoto();
        this.price = product.getPrice().doubleValue();
        this.storeqty = product.getStoreqty();

        return this;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public int getStoreqty() {
        return storeqty;
    }

    public void setStoreqty(int storeqty) {
        this.storeqty = storeqty;
    }
}
