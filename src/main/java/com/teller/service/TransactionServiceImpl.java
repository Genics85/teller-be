package com.teller.service;

import com.teller.exception.ResourceNotFound;
import com.teller.model.Transaction;
import com.teller.repository.TransactionRepo;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {

    TransactionRepo transactionRepo;

    public TransactionServiceImpl(TransactionRepo transactionRepo) {
        this.transactionRepo = transactionRepo;
    }

    @Override
    public Transaction add(Transaction transaction) {
        Transaction newTransaction = new Transaction();
        newTransaction.setAccountNumber(transaction.getAccountNumber());
        newTransaction.setTellerId(transaction.getTellerId());
        newTransaction.setTransactionType(transaction.getTransactionType());
        newTransaction.setAmount(transaction.getAmount());

        return transactionRepo.save(newTransaction);
    }

    @Override
    public Transaction get(Long id) {

        return transactionRepo.findById(id).orElseThrow(()-> new ResourceNotFound("Transaction","id",id));
    }

    @Override
    public List<Transaction> getTellerDayTransaction(Long id, LocalDateTime date) {

        return transactionRepo.findByTellerIdAndDate(id,date.toLocalDate());
    }
    @Override
     public List<Transaction> getTellerToDayTransactions(Long id){
        return getTellerDayTransaction(id,LocalDateTime.now());
    }
}
