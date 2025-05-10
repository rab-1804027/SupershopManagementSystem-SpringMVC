package com.bappi.supershopmanagementsystem.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

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
//    @JsonManagedReference
//    @OneToMany(mappedBy = "sale", fetch = FetchType.EAGER)
//    private Set<SaleDetails> saleDetails;

    public Sale(User user, double totalPrice) {
        this.user = user;
        this.totalPrice = totalPrice;
        this.saleTime = LocalDateTime.now();
    }
}
