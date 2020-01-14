package com.accenture.flowershop.back.business.dao;

import com.accenture.flowershop.back.entity.Product;

import java.util.List;

public interface ProductDao {
    List<Product> getProductList();
    List<Product> searchProductsByName(String productName);
    List<Product> searchProductsByPriceRange(String minPrice, String maxPrice);
    Product findProduct(String productId);
}
