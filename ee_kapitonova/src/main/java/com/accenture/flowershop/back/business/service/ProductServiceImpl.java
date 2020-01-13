package com.accenture.flowershop.back.business.service;

import com.accenture.flowershop.back.business.dao.ProductDao;
import com.accenture.flowershop.back.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDao productDao;

    private List<Product> productList;

    public List<Product> getProductList() {
        return productDao.getProductList();
    }

    public Product findProduct(String productId) {
        return productDao.findProduct(productId);
    }
}
