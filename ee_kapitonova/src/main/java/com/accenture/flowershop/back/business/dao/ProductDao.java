package com.accenture.flowershop.back.business.dao;

import com.accenture.flowershop.back.entity.Product;

import java.util.List;

public interface ProductDao {
    List<Product> getProductList();
    Product findProduct(String productId);
}
