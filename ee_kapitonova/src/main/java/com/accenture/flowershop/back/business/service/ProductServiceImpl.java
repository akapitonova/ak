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

    public List<Product> getProductList() {
        return productDao.getProductList();
    }

    public List<Product> searchProducts(String name, String minPrice, String maxPrice) {
        return productDao.searchProducts(name, minPrice, maxPrice);
    }

    public Product findProduct(String productId) {
        return productDao.findProduct(productId);
    }

    @Override
    public void increaseStockSize(int count) {
        productDao.increaseStockSize(count);
    }
}
