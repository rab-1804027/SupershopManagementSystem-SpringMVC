package com.bappi.supershopmanagementsystem.dto;

import lombok.Data;
import lombok.Getter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class SaleDto {
    @Getter
    private int id;
    @Getter
    private double totalPrice;
    @Getter
    private String saleTime;

    public SaleDto(int id, double totalPrice, LocalDateTime saleTime) {
        this.id = id;
        this.totalPrice = totalPrice;
        this.saleTime = formateTime(saleTime);
    }

    private String formateTime(LocalDateTime time)
    {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM d, yyyy HH:mm:ss");
        return time.format(formatter);
    }

    public void setSaleTime(LocalDateTime saleTime) {
        this.saleTime = formateTime(saleTime);
    }
}

