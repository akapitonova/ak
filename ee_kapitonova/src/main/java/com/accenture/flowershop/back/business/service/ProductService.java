package com.accenture.flowershop.back.business.service;

import com.accenture.flowershop.back.entity.Product;

import java.util.List;

public interface ProductService {
    List<Product> getProductList();
    Product findProduct(String productId);
}
