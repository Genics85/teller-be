package com.teller.model.dto;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class CreateTellerDto {
    private String firstName;
    private String lastName;
    @Column(unique = true)
    private String email;
    @Column(unique = true)
    private String phoneNumber;
    private String password;
}
