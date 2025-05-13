package com.bappi.supershopmanagementsystem.model;

import com.bappi.supershopmanagementsystem.enums.ApprovalStatus;
import jdk.jfr.Enabled;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String email;
    private String username;
    private String password;
    private String role;
    private LocalDateTime registrationTime;
    @Enumerated(EnumType.STRING)
    private ApprovalStatus approvalStatus;
    @ToString.Exclude
    @OneToMany(mappedBy = "user",fetch = FetchType.EAGER)
    private List<Product> productList;

    public User(String name, String email, String username, String password) {
        this.name = name;
        this.email = email;
        this.username = username;
        this.password = password;
        this.role = "null";
        this.registrationTime = LocalDateTime.now();
        this.approvalStatus = ApprovalStatus.PENDING;
    }
}
