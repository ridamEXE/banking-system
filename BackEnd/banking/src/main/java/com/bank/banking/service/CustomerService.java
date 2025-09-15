package com.bank.banking.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bank.banking.dto.UpdateDetailsRequest;
import com.bank.banking.entity.Customer;
import com.bank.banking.entity.Transaction;
import com.bank.banking.repository.CustomerRepository;
import com.bank.banking.repository.TransactionRepository;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;
    
    @Autowired
    private TransactionRepository transactionRepository;

    @Transactional
    public void transferMoney(String fromAccountNumber, String toAccountNumber, BigDecimal amount, String password) {
    	System.out.println(fromAccountNumber+"accno");
    	System.out.println(fromAccountNumber.getClass().getName()+"accno");
        Customer fromCustomer = customerRepository.findByAccountNumber(fromAccountNumber)
                .orElseThrow(() -> new RuntimeException("Your account was not found."));
        
        if(!fromCustomer.getPassword().equals(password)) {
        	throw new RuntimeException("Incorrect password. Transfer failed.");
        }

        Customer toCustomer = customerRepository.findByAccountNumber(toAccountNumber)
                .orElseThrow(() -> new RuntimeException("Receiver's account number not found."));

        BigDecimal fromBalance = new BigDecimal(fromCustomer.getAccountBalance());
        BigDecimal toBalance = new BigDecimal(toCustomer.getAccountBalance());

        if (fromBalance.compareTo(amount) < 0) {
            throw new RuntimeException("Insufficient funds for this transfer.");
        }

        if (fromBalance.subtract(amount).compareTo(new BigDecimal("500")) < 0) {
            throw new RuntimeException("Transfer failed. A minimum balance of 500 must be maintained.");
        }

        BigDecimal newFromBalance = fromBalance.subtract(amount);
        BigDecimal newToBalance = toBalance.add(amount);

        fromCustomer.setAccountBalance(newFromBalance.toPlainString());
        toCustomer.setAccountBalance(newToBalance.toPlainString());
        
        // LOG DEBIT TRANSACTION
        Transaction debitTx = new Transaction();
        debitTx.setAccountNumber(fromAccountNumber);
        debitTx.setTimestamp(LocalDateTime.now());
        debitTx.setType("TRANSFER_DEBIT");
        debitTx.setAmount(amount.negate());
        debitTx.setDescription("Transfer to Acc: " + toAccountNumber);
        transactionRepository.save(debitTx);

        // LOG CREDIT TRANSACTION
        Transaction creditTx = new Transaction();
        creditTx.setAccountNumber(toAccountNumber);
        creditTx.setTimestamp(LocalDateTime.now());
        creditTx.setType("TRANSFER_CREDIT");
        creditTx.setAmount(amount);
        creditTx.setDescription("Transfer from Acc: " + fromAccountNumber);
        transactionRepository.save(creditTx);

        customerRepository.save(fromCustomer);
        customerRepository.save(toCustomer);
    }
    
    @Transactional
    public void resetPassword(String customerId, String accountNumber, String oldPassword, String newPassword) {

        Customer customer = customerRepository.findByCustomerIdAndAccountNumber(customerId, accountNumber)
                .orElseThrow(() -> new RuntimeException("Invalid Customer ID or Account Number."));

        if (!customer.getPassword().equals(oldPassword)) {
            throw new RuntimeException("Incorrect old password.");
        }

        customer.setPassword(newPassword);
        customerRepository.save(customer);
    }
    
    @Transactional
    public Customer updateCustomerDetails(String customerId, UpdateDetailsRequest request) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found. Please log in again."));
        
        if (request.getDateOfBirth() != null) {
            if (Period.between(request.getDateOfBirth(), LocalDate.now()).getYears() < 18) {
                throw new RuntimeException("Customer must be at least 18 years old.");
            }
        } else {
            throw new RuntimeException("Date of Birth is required.");
        }

        if (!customer.getPassword().equals(request.getPassword())) {
            throw new RuntimeException("Incorrect password.");
        }

        customer.setAddress(request.getAddress());
        customer.setEmail(request.getEmail());
        customer.setMartialStatus(request.getMaritalStatus());
        customer.setDateOfBirth(request.getDateOfBirth());

        return customerRepository.save(customer);
    }
    
    public Customer getCustomerByAccountNumber(String accountNumber) {
    	return customerRepository.findByAccountNumber(accountNumber)
    			.orElseThrow(() -> new RuntimeException("Customer account not found."));
    }
}