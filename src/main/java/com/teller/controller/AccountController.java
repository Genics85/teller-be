package com.teller.controller;

import com.teller.model.Account;
import com.teller.model.dto.CreateAccountDto;
import com.teller.model.dto.UpdateAccountDto;
import com.teller.service.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/account")
public class AccountController {

    AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Account> get(@PathVariable Long id){
        return new ResponseEntity<>(accountService.get(id),HttpStatus.FOUND);
    }
    @PutMapping
    public ResponseEntity<Account> update(@RequestBody UpdateAccountDto updateAccountDto){
        return new ResponseEntity<>(accountService.update(updateAccountDto),HttpStatus.OK);
    }
}
