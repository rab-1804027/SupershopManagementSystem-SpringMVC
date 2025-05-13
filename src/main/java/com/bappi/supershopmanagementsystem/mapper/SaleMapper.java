package com.bappi.supershopmanagementsystem.mapper;

import com.bappi.supershopmanagementsystem.dto.SaleDto;
import com.bappi.supershopmanagementsystem.model.Sale;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class SaleMapper {
    public SaleDto toDto(Sale sale) {
        int id = sale.getId();
        double totalPrice = sale.getTotalPrice();
        LocalDateTime saleTime = sale.getSaleTime();
        return new SaleDto(id, totalPrice, saleTime);
    }
}
