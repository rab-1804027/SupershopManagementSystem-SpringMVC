package com.bappi.supershopmanagementsystem.dao;

import com.bappi.supershopmanagementsystem.model.SaleDetails;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


@Repository
public class SaleDetailsDao {

    @PersistenceContext
    private EntityManager entityManager;

    public void save(SaleDetails saleDetails){
        entityManager.persist(saleDetails);
    }


}
