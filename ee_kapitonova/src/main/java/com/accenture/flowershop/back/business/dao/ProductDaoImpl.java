package com.accenture.flowershop.back.business.dao;

import com.accenture.flowershop.back.entity.Product;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional
public class ProductDaoImpl implements ProductDao {

    @PersistenceContext
    private EntityManager em;

    public List<Product> getProductList() {
        List<Product> productList = em.createQuery("SELECT t FROM "+Product.class.getName()+" t").getResultList();
        return productList;
    }

    public Product findProduct(String productId) {
        return em.find(Product.class, productId);
    }

}
