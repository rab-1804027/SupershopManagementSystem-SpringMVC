package com.bappi.supershopmanagementsystem.mapper;

import com.bappi.supershopmanagementsystem.dto.SaleDetailsDto;
import com.bappi.supershopmanagementsystem.model.SaleDetails;
import org.springframework.stereotype.Component;

@Component
public class SaleDetailsMapper {
    public SaleDetailsDto toDto(SaleDetails saleDetails) {
        String productName = saleDetails.getProduct().getName();
        double unitPrice = saleDetails.getUnitPrice();
        int quantity = saleDetails.getQuantity();
        double price = saleDetails.getPrice();
        return new SaleDetailsDto(productName, unitPrice, quantity, price);
    }
}
