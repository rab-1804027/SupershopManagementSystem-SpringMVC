package com.bappi.supershopmanagementsystem.dao;

import com.bappi.supershopmanagementsystem.dto.ProductDto;
import com.bappi.supershopmanagementsystem.model.Product;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;

import jakarta.persistence.PersistenceContext;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class ProductDao {

    @PersistenceContext
    EntityManager entityManager;

    public void save(Product product){
        entityManager.persist(product);
    }

    public Product findById(int id){
        return entityManager.find(Product.class, id);
    }

    public void update(Product product){
        String query = "UPDATE Product p SET p.name = :name, p.price = :price, p.stockQuantity = :stockQuantity WHERE p.id = :id";
        entityManager.createQuery(query)
                .setParameter("name", product.getName())
                .setParameter("price", product.getPrice())
                .setParameter("stockQuantity", product.getStockQuantity())
                .setParameter("id", product.getId())
                .executeUpdate();

    }

    public List findByUserId(int userId){
        String query = "Select p From Product p where p.user.id= :userId";
        return entityManager.createQuery(query)
                .setParameter("userId", userId)
                .getResultList();
    }
}
