package com.bappi.supershopmanagementsystem.service;

import com.bappi.supershopmanagementsystem.dao.SaleDao;
import com.bappi.supershopmanagementsystem.dao.SaleDetailsDao;
import com.bappi.supershopmanagementsystem.model.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class SaleDetailsService {
    private final SaleDetailsDao saleDetailsDao;

    public void save(Sale sale, ProductCart cart){
        for(CartItem cartItem : cart.getCartItems()){
            Product product = cartItem.getProduct();
            double unitPrice = cartItem.getProduct().getPrice();
            int quantity = cartItem.getQuantity();
            double price = cartItem.getPrice();
            saleDetailsDao.save(new SaleDetails(sale, product, unitPrice, quantity, price));
        }
    }
}
