package com.bappi.supershopmanagementsystem.dao;

import com.bappi.supershopmanagementsystem.dto.SaleDto;
import com.bappi.supershopmanagementsystem.model.Sale;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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

    public List<Sale> findAllByUserId(int userId){
        String query = "Select s From Sale s where s.user.id= :userId";
        return entityManager.createQuery(query)
                .setParameter("userId", userId)
                .getResultList();
    }
}
