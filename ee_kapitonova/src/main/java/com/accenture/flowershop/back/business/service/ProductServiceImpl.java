package com.accenture.flowershop.back.business.service;

import com.accenture.flowershop.back.business.dao.ProductDao;
import com.accenture.flowershop.back.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDao productDao;

    private List<Product> productList;

    public List<Product> getProductList() {
        return productDao.getProductList();
    }

    public List<Product> searchProductsByPriceRange(String minPrice, String maxPrice) {
        return productDao.searchProductsByPriceRange(minPrice, maxPrice);
    }

    public List<Product> searchProductsByName(String productName) {
        return productDao.searchProductsByName(productName);
    }

    public Product findProduct(String productId) {
        return productDao.findProduct(productId);
    }
}
