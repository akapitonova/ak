package com.accenture.flowershop.back.business.service;

import com.accenture.flowershop.back.entity.Product;

import java.util.List;

public interface ProductService {
    List<Product> getProductList();
    List<Product> searchProducts(String name, String minPrice, String maxPrice);
    Product findProduct(String productId);
    void increaseStockSize(int count);
}
