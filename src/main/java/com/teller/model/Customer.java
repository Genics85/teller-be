package com.teller.model;


import jakarta.persistence.*;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "customer")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String dateOfBirth;
    private String address;
    @Column(unique = true)
    private String phoneNumber;
    @Column(unique = true)
    private String email;
    @Column(unique = true)
    private String accountNumber = null;
    @DateTimeFormat
    private LocalDateTime date = LocalDateTime.now();
}