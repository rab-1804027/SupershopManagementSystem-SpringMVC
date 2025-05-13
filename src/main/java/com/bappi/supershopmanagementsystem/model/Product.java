package com.bappi.supershopmanagementsystem.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import jakarta.persistence.*;

@Entity
@Table(name = "products")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;
    private String name;
    private double price;
    private int stockQuantity;

    public Product(User user, String name, double price, int stockQuantity) {
        this.user = user;
        this.name = name;
        this.price = price;
        this.stockQuantity = stockQuantity;
    }

}
