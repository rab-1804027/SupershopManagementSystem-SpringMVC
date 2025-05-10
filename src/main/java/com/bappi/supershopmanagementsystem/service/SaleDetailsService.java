package com.bappi.supershopmanagementsystem.service;

import com.bappi.supershopmanagementsystem.dao.SaleDao;
import com.bappi.supershopmanagementsystem.dao.SaleDetailsDao;
import com.bappi.supershopmanagementsystem.dto.SaleDetailsDto;
import com.bappi.supershopmanagementsystem.mapper.SaleDetailsMapper;
import com.bappi.supershopmanagementsystem.model.*;
import com.itextpdf.text.DocumentException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class SaleDetailsService {
    private final SaleDetailsDao saleDetailsDao;
    private final SaleService saleService;
    private final SaleDetailsMapper saleDetailsMapper;

    public void save(Sale sale, ProductCart cart){
        for(CartItem cartItem : cart.getCartItems()){
            Product product = cartItem.getProduct();
            double unitPrice = cartItem.getProduct().getPrice();
            int quantity = cartItem.getQuantity();
            double price = cartItem.getPrice();
            saleDetailsDao.save(new SaleDetails(sale, product, unitPrice, quantity, price));
        }
    }

    public List<SaleDetailsDto> findAllBySale(Sale sale){
        List<SaleDetails> saleDetails= saleDetailsDao.findAllByUser(sale);
        List<SaleDetailsDto> saleDetailDtos = new ArrayList<>();
        for(SaleDetails saleDetail : saleDetails){
            saleDetailDtos.add(saleDetailsMapper.toDto(saleDetail));
        }
        return saleDetailDtos;
    }

}
