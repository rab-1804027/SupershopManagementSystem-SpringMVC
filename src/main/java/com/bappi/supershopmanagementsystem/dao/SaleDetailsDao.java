package com.bappi.supershopmanagementsystem.dao;

import com.bappi.supershopmanagementsystem.model.Sale;
import com.bappi.supershopmanagementsystem.model.SaleDetails;
import com.bappi.supershopmanagementsystem.model.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;


@Repository
public class SaleDetailsDao {

    @PersistenceContext
    private EntityManager entityManager;

    public void save(SaleDetails saleDetails){
        entityManager.persist(saleDetails);
    }

    public List<SaleDetails> findAllByUser(Sale sale) {
        String jpql = "SELECT sd FROM SaleDetails sd where sd.sale=:sale";
        return entityManager.createQuery(jpql, SaleDetails.class)
                .setParameter("sale", sale)
                .getResultList();
    }

}
