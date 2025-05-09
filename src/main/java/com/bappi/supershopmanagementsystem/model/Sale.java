package com.bappi.supershopmanagementsystem.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "sales")
@Data
@NoArgsConstructor
public class Sale {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ToString.Exclude
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "userId")
    private User user;
    private double totalPrice;
    private LocalDateTime saleTime;

    public Sale(User user, double totalPrice) {
        this.user = user;
        this.totalPrice = totalPrice;
        this.saleTime = LocalDateTime.now();
    }
}
