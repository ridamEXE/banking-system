package com.bank.banking.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bank.banking.dto.ResetPasswordRequest;
import com.bank.banking.dto.TransferRequest;
import com.bank.banking.dto.UpdateDetailsRequest;
import com.bank.banking.entity.Customer;
import com.bank.banking.entity.Transaction;
import com.bank.banking.repository.TransactionRepository;
import com.bank.banking.service.CustomerService;

@RestController
@RequestMapping("/api/customer")
@CrossOrigin(origins = "http://localhost:4200")
public class CustomerController {

    @Autowired
    private CustomerService customerService;
    
    @Autowired
    private TransactionRepository transactionRepository;

    @PostMapping("/transfer")
    public ResponseEntity<Map<String,String>> transferMoney(@RequestBody TransferRequest transferRequest) {
        try {
            	customerService.transferMoney(
                transferRequest.getFromAccountNumber(),
                transferRequest.getToAccountNumber(),
                transferRequest.getAmount(),
                transferRequest.getPassword()
            );
            return ResponseEntity.ok(Map.of("message","Transfer successful!"));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of("error",e.getMessage()));
        }
    }
    
    @PostMapping("/reset-password")
    public ResponseEntity<String> resetPassword(@RequestBody ResetPasswordRequest request) {
        try {
            customerService.resetPassword(
                request.getCustomerId(),
                request.getAccountNumber(),
                request.getOldPassword(),
                request.getNewPassword()
            );
            return ResponseEntity.ok("Password reset successfully!");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    
    @PutMapping("/update-details/{customerId}")
    public ResponseEntity<?> updateDetails(@PathVariable String customerId, @RequestBody UpdateDetailsRequest request) {
        try {
        	
            Customer updatedCustomer = customerService.updateCustomerDetails(customerId, request);
            updatedCustomer.setPassword(null);
            return ResponseEntity.ok(updatedCustomer);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    
    @GetMapping("/{accountNumber}/statement")
    public ResponseEntity<Page<Transaction>> getAccountStatement(
            @PathVariable String accountNumber,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {
        
        Page<Transaction> transactions = transactionRepository.findByAccountNumberOrderByTimestampDesc(
            accountNumber, PageRequest.of(page, size)
        );
        return ResponseEntity.ok(transactions);
    }
    
    @GetMapping("/details/{accountNumber}")
    public ResponseEntity<Customer> viewBalance(@PathVariable String accountNumber) {
        
        try {
        	System.out.println(accountNumber);
        	Customer customer=customerService.getCustomerByAccountNumber(accountNumber);
        	customer.setPassword(null);
        	return ResponseEntity.ok(customer);
        }
        catch(RuntimeException e){
        	return ResponseEntity.notFound().build();
        }
        
    }
}
