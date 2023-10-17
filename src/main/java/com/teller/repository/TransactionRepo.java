package com.teller.repository;

import com.teller.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface TransactionRepo extends JpaRepository<Transaction, Long> {
    @Query("SELECT t FROM Transaction t WHERE t.tellerId = :tellerId AND DATE(t.date) = :date")
    List<Transaction> findByTellerIdAndDate(Long tellerId, LocalDate date);
}
