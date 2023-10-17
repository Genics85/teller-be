package com.teller.repository;

import com.teller.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepo extends JpaRepository<Account,Long> {
    Account findByAccountNumber(String accountNumber);
}

