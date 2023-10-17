package com.teller.controller;

import com.teller.model.Transaction;
import com.teller.service.TransactionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/transaction")
public class TransactionController {

    TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping("/{teller-id}")
    ResponseEntity<List<Transaction>> getTellerDayTransaction(@PathVariable("teller-id") Long id){
        return new ResponseEntity<>(transactionService.getTellerToDayTransactions(id), HttpStatus.FOUND);
    }
}
