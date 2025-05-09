package com.bappi.supershopmanagementsystem.service;

import com.bappi.supershopmanagementsystem.dao.SaleDao;
import com.bappi.supershopmanagementsystem.dto.SaleDto;
import com.bappi.supershopmanagementsystem.mapper.SaleMapper;
import com.bappi.supershopmanagementsystem.model.Sale;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class SaleService {

    private final SaleDao saleDao;
    private final SaleMapper saleMapper;

    public Sale save(Sale sale){
        return saleDao.save(sale);
    }

    public List<SaleDto> findAllByUserId(int userId) {
        List<Sale> sales = saleDao.findAllByUserId(userId);
        List<SaleDto> saleRecords = new ArrayList<>();
        for(Sale sale : sales){
            saleRecords.add(saleMapper.toDto(sale));
        }
        return saleRecords;
    }

}
