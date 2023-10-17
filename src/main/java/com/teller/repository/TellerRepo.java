package com.teller.repository;

import com.teller.model.Teller;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TellerRepo extends JpaRepository<Teller, Long> {
    Teller findByEmail(String email);
}
