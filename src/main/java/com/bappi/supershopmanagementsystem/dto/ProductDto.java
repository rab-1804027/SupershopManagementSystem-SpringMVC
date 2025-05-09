package com.bappi.supershopmanagementsystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@NoArgsConstructor
public class ProductDto {
    String name;
    double price;
    int stockQuantity;
}
