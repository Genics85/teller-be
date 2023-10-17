package com.teller.model.dto;

import com.teller.enums.TransactionType;
import lombok.Data;

@Data
public class UpdateAccountDto {
    TransactionType type;
    String accountNumber;
    Long tellerId;
    double amount;
}
