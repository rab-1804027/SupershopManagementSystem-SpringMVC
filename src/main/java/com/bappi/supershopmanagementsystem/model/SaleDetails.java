package com.bappi.supershopmanagementsystem.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import jakarta.persistence.*;

@Entity
@Table(name = "saleDetails")
@Data
@RequiredArgsConstructor
public class SaleDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @JsonBackReference
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="saleId")
    private Sale sale;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="productId")
    private Product product;
    private double unitPrice;
    private int quantity;
    private double price;

    public SaleDetails(Sale sale, Product product, double unitPrice, int quantity, double price) {
        this.sale = sale;
        this.product = product;
        this.unitPrice = unitPrice;
        this.quantity = quantity;
        this.price = price;
    }

}
