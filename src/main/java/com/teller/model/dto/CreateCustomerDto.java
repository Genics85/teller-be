package com.teller.model.dto;

import lombok.Data;

@Data
public class CreateCustomerDto {
    private String firstName;
    private String lastName;
    private String dateOfBirth;
    private String address;
    private String phoneNumber;
    private String email;
}
