package com.bank.banking.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bank.banking.entity.Customer;
import com.bank.banking.repository.CustomerRepository;

@Service
public class RegistrationService {

    @Autowired
    private CustomerRepository customerRepository;

    @Transactional
    public void registerCustomer(String ssnId, String accountNumber, String password) {
        // Find the customer by SSN ID and Account Number
        Customer customer = customerRepository.findByCustomerSSNIdAndAccountNumber(ssnId, accountNumber)
                .orElseThrow(() -> new RuntimeException("Invalid SSN ID or Account Number. Please contact the bank."));
        
        // Check if a password has already been set
        if (customer.getPassword() != null && !customer.getPassword().isEmpty()) {
            throw new RuntimeException("This account has already been registered. Please use the login or reset password pages.");
        }

        // Set the new password and save
        customer.setPassword(password);
        customerRepository.save(customer);
    }
}