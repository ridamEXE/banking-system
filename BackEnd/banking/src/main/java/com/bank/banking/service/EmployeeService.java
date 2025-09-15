package com.bank.banking.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bank.banking.entity.Customer;
import com.bank.banking.entity.Transaction;
import com.bank.banking.repository.CustomerRepository;
import com.bank.banking.repository.TransactionRepository;

@Service
public class EmployeeService {

    @Autowired
    private CustomerRepository customerRepository;
    
    @Autowired 
    private TransactionRepository transactionRepository;

    @Transactional
    public Customer depositMoney(String accountNumber, BigDecimal amountToDeposit) {
        Customer customer = customerRepository.findByAccountNumber(accountNumber)
                .orElseThrow(() -> new RuntimeException("Customer account not found with number: " + accountNumber));
        
        BigDecimal currentBalance = new BigDecimal(customer.getAccountBalance());
        BigDecimal newBalance = currentBalance.add(amountToDeposit);
        customer.setAccountBalance(newBalance.toPlainString());
        
        //transaction
        Transaction tx = new Transaction();
        tx.setAccountNumber(accountNumber);
        tx.setTimestamp(LocalDateTime.now());
        tx.setType("DEPOSIT");
        tx.setAmount(amountToDeposit);
        tx.setDescription("Deposit by bank employee");
        transactionRepository.save(tx);

        return customerRepository.save(customer);
    }
    
    @Transactional
    public Customer withdrawMoney(String accountNumber, BigDecimal amountToWithdraw) {
        Customer customer = customerRepository.findByAccountNumber(accountNumber)
                .orElseThrow(() -> new RuntimeException("Customer account not found with number: " + accountNumber));

        BigDecimal currentBalance = new BigDecimal(customer.getAccountBalance());

        if (amountToWithdraw.compareTo(new BigDecimal("500")) < 0) {
            throw new RuntimeException("Minimum withdrawal amount is 500.");
        }
        
        if (currentBalance.compareTo(new BigDecimal("1000")) < 0) {
            throw new RuntimeException("Cannot withdraw. Account balance is less than 1000.");
        }

        if (amountToWithdraw.compareTo(currentBalance) > 0) {
            throw new RuntimeException("Withdrawal amount cannot be greater than the current balance.");
        }

        BigDecimal newBalance = currentBalance.subtract(amountToWithdraw);
        customer.setAccountBalance(newBalance.toPlainString());
        
        //transaction
        Transaction tx = new Transaction();
        tx.setAccountNumber(accountNumber);
        tx.setTimestamp(LocalDateTime.now());
        tx.setType("WITHDRAWAL");
        tx.setAmount(amountToWithdraw.negate()); // Store withdrawals as negative
        tx.setDescription("Withdrawal by bank employee");
        transactionRepository.save(tx);
        
        return customerRepository.save(customer);
    }
    
    public Page<Customer> findAllCustomers(Pageable pageable){
    	return customerRepository.findAll(pageable);
    }
}