package com.bank.banking.repository;
import com.bank.banking.entity.Customer;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, String> {
	Optional<Customer> findByCustomerIdAndAccountNumber(String customerId, String accountNumber);
	Optional<Customer> findByCustomerSSNId(String ssnId);
	Optional<Customer> findByAccountNumber(String accountNumber);
	Page<Customer> findByCreatedByManagerId(String managerId, Pageable pageable);
	Optional<Customer> findByCustomerSSNIdAndAccountNumber(String ssnId, String accountNumber);
}
