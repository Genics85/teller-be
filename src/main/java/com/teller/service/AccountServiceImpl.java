package com.teller.service;

import com.teller.enums.TransactionType;
import com.teller.exception.LowBalanceException;
import com.teller.exception.ResourceNotFound;
import com.teller.model.Account;
import com.teller.model.Customer;
import com.teller.model.Teller;
import com.teller.model.Transaction;
import com.teller.model.dto.CreateAccountDto;
import com.teller.model.dto.UpdateAccountDto;
import com.teller.repository.AccountRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class AccountServiceImpl implements AccountService {

    AccountRepo accountRepo;
    @Autowired
    TellerService tellerService;
    @Autowired
    TransactionService transactionService;

    public AccountServiceImpl(AccountRepo accountRepo) {
        this.accountRepo = accountRepo;
    }


    @Override
    public Account get(Long id) {
        return accountRepo.findById(id).orElseThrow(()-> new ResourceNotFound("Account","id",id));
    }

    @Override
    public Account add(CreateAccountDto createAccountDto) {
        Account newAccount = new Account();
        String accountNumber;

        do {
            accountNumber = String.format("%08d", new Random().nextInt(100000000));
        } while (this.getByNumber(accountNumber) != null);

        newAccount.setAccountNumber(accountNumber);
        newAccount.setCustomerEmail(createAccountDto.getCustomerEmail());

        return accountRepo.save(newAccount);
    }

    @Override
    public Account update(UpdateAccountDto updateAccountDto) {
        Account toBeUpdated = this.getByNumber(updateAccountDto.getAccountNumber());
        Teller teller = tellerService.get(updateAccountDto.getTellerId());

        double newBalance;
        if(updateAccountDto.getType() == TransactionType.DEPOSIT){
           newBalance = toBeUpdated.getBalance() + updateAccountDto.getAmount();
        }else{
            newBalance = toBeUpdated.getBalance() - updateAccountDto.getAmount();
            if(newBalance < 0) throw new LowBalanceException("Low balance exception on account",updateAccountDto.getAccountNumber());
        }
        toBeUpdated.setBalance(newBalance);
        Account updateAccount =  accountRepo.save(toBeUpdated);

        Transaction transaction = new Transaction();
        transaction.setTransactionType(updateAccountDto.getType());
        transaction.setTellerId(teller.getId());
        transaction.setAmount(updateAccountDto.getAmount());
        transaction.setAccountNumber(updateAccountDto.getAccountNumber());
        transactionService.add(transaction);

        return updateAccount;
    }

    @Override
    public Account getByNumber(String accountNumber) {

        return accountRepo.findByAccountNumber(accountNumber);
    }
}
