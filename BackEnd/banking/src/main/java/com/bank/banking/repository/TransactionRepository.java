package com.bank.banking.repository;
import com.bank.banking.entity.Transaction;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    Page<Transaction> findByAccountNumberOrderByTimestampDesc(String accountNumber, Pageable pageable);
}
