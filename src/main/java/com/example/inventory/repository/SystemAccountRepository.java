package com.example.inventory.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.inventory.model.SystemAccount;

import java.util.Optional;

public interface SystemAccountRepository extends JpaRepository<SystemAccount, Integer> {
    Optional<SystemAccount> findByEmail(String email);

    Optional<SystemAccount> findByEmailAndPassword(String email, String password);
}
