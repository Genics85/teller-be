package com.teller.controller;

import com.teller.model.Account;
import com.teller.model.Customer;
import com.teller.model.dto.CreateAccountDto;
import com.teller.model.dto.CreateCustomerDto;
import com.teller.service.AccountService;
import com.teller.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/api/customer")
public class CustomerController {

    CustomerService customerService;
    @Autowired
    AccountService accountService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping
    public ResponseEntity<Customer> create(@RequestBody CreateCustomerDto createCustomerDto){
        Customer createdCustomer = customerService.add(createCustomerDto);
        String accountNumber;
        if(createdCustomer != null){
            CreateAccountDto createAccountDto=new CreateAccountDto();
            createAccountDto.setCustomerEmail(createdCustomer.getEmail());
            Account createdAccount = accountService.add(createAccountDto);
            accountNumber = createdAccount.getAccountNumber();
            createdCustomer.setAccountNumber(accountNumber);
            customerService.update(createdCustomer);
        }
        Customer updatedCustomer = customerService.get(createdCustomer.getId());
        return new ResponseEntity<>(updatedCustomer, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Customer> get(@PathVariable("id") Long id){
        return new ResponseEntity<>(customerService.get(id),HttpStatus.FOUND);
    }
}
