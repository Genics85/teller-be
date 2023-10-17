package com.teller.service;

import com.teller.model.Transaction;

import java.time.LocalDateTime;
import java.util.List;

public interface TransactionService {
    Transaction add(Transaction transaction);
    Transaction get(Long id);
    List<Transaction> getTellerDayTransaction(Long id ,LocalDateTime date);

    List<Transaction> getTellerToDayTransactions(Long id);
}
