package com.bappi.supershopmanagementsystem.dao;

import com.bappi.supershopmanagementsystem.dto.SaleDto;
import com.bappi.supershopmanagementsystem.model.Sale;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class SaleDao {

    @PersistenceContext
    private EntityManager entityManager;

    public Sale save(Sale sale){
        entityManager.persist(sale);
        entityManager.flush();
        entityManager.refresh(sale);
        return sale;
    }

    public Sale findById(int id){
        return entityManager.find(Sale.class, id);
    }

    public List<Sale> findAllByUserId(int userId){
        String query = "Select s From Sale s where s.user.id= :userId order by id desc";
        return entityManager.createQuery(query)
                .setParameter("userId", userId)
                .getResultList();
    }

}
