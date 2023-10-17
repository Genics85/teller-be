package com.teller.repository;

import com.teller.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepo extends JpaRepository<Customer, Long> {
    Customer findByAccountNumber(String accountNumber);
    Customer findByEmail(String email);
}
