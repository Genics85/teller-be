package com.teller.model;

import com.teller.enums.TransactionType;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "transaction")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String accountNumber;
    private Long tellerId;
    private TransactionType transactionType;
    private double amount;
    @DateTimeFormat
    private LocalDateTime date = LocalDateTime.now();

}