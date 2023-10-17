package com.teller.service;

import com.teller.exception.ResourceNotFound;
import com.teller.model.Account;
import com.teller.model.Customer;
import com.teller.model.dto.CreateAccountDto;
import com.teller.model.dto.CreateCustomerDto;
import com.teller.repository.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {

    CustomerRepo customerRepo;
    @Autowired
    AccountService accountService;


    public CustomerServiceImpl(CustomerRepo customerRepo) {
        this.customerRepo = customerRepo;
    }

    @Override
    public Customer add(CreateCustomerDto createCustomerDto) {

        Customer newCustomer = new Customer();
        newCustomer.setFirstName(createCustomerDto.getFirstName());
        newCustomer.setLastName(createCustomerDto.getLastName());
        newCustomer.setPhoneNumber(createCustomerDto.getPhoneNumber());
        newCustomer.setDateOfBirth(createCustomerDto.getDateOfBirth());
        newCustomer.setAddress(createCustomerDto.getAddress());
        newCustomer.setEmail(createCustomerDto.getEmail());
        return customerRepo.save(newCustomer) ;
    }

    @Override
    public Customer get(Long id) {

        return customerRepo.findById(id).orElseThrow(()-> new ResourceNotFound("Customer","id",id));
    }

    @Override
    public Customer getByAccountNumber(String accountNumber) {

        return customerRepo.findByAccountNumber(accountNumber);
    }

    @Override
    public Customer getByEmail(String email) {
        return customerRepo.findByEmail(email);
    }

    @Override
    public Customer update(Customer customer) {
        Customer toBeUpdated = this.get(customer.getId());
        toBeUpdated.setAccountNumber(customer.getAccountNumber());
        toBeUpdated.setFirstName(customer.getFirstName());
        toBeUpdated.setLastName(customer.getLastName());
        toBeUpdated.setPhoneNumber(customer.getPhoneNumber());
        toBeUpdated.setDateOfBirth(customer.getDateOfBirth());
        toBeUpdated.setAddress(customer.getAddress());
        toBeUpdated.setEmail(customer.getEmail());
        return customerRepo.save(toBeUpdated);
    }


}
