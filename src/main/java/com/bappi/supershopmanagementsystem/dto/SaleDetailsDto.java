package com.bappi.supershopmanagementsystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
public class SaleDetailsDto {
    private String productName;
    private double unitPrice;
    private int quantity;
    private double price;
}
