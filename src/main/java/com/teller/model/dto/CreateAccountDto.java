package com.teller.model.dto;

import com.teller.model.Customer;
import lombok.Data;

@Data
public class CreateAccountDto {
    private String customerEmail;
}
