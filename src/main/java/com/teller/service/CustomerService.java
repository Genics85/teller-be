package com.teller.service;

import com.teller.model.Account;
import com.teller.model.Customer;
import com.teller.model.dto.CreateCustomerDto;

public interface CustomerService {

    Customer add(CreateCustomerDto createCustomerDto);
    Customer get(Long id);
    Customer getByAccountNumber(String accountNumber);

    Customer getByEmail(String email);
    Customer update(Customer customer);
}
