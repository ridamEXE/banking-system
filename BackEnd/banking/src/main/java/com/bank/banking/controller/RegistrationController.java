package com.bank.banking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bank.banking.dto.CustomerRegistrationRequest;
import com.bank.banking.service.RegistrationService;

@RestController
@RequestMapping("/api/register")
@CrossOrigin(origins = "http://localhost:4200")
public class RegistrationController {

    @Autowired
    private RegistrationService registrationService;

    @PostMapping("/customer")
    public ResponseEntity<String> registerCustomer(@RequestBody CustomerRegistrationRequest request) {
        try {
            registrationService.registerCustomer(
                request.getCustomerSSNId(),
                request.getAccountNumber(),
                request.getPassword()
            );
            return ResponseEntity.ok("Registration successful! You can now log in.");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}