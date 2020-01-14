package com.accenture.flowershop.back.business.dao;

import com.accenture.flowershop.back.entity.Product;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.math.BigDecimal;
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

    public List<Product> searchProductsByName(String name) {
        List<Product> productList = em.createQuery("SELECT t FROM "+Product.class.getName()+" t WHERE t.name LIKE :name")
                .setParameter("name", "%"+name+"%")
                .getResultList();
        return productList;
    }

    public List<Product> searchProductsByPriceRange(String minPrice, String maxPrice) {
        List<Product> productList = em.createQuery("SELECT t FROM "+Product.class.getName()+" t WHERE t.price >= :minPrice " +
                "AND t.price <= :maxPrice")
                .setParameter("minPrice", new BigDecimal(minPrice))
                .setParameter("maxPrice", new BigDecimal(maxPrice))
                .getResultList();
        return productList;
    }

    public Product findProduct(String productId) {
        return em.find(Product.class, productId);
    }

}
