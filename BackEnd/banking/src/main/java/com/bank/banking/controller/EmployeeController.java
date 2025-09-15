package com.bank.banking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bank.banking.dto.DepositRequest;
import com.bank.banking.entity.Customer;
import com.bank.banking.service.EmployeeService;

@RestController
@RequestMapping("/api/employee")
@CrossOrigin(origins = "http://localhost:4200")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/deposit")
    public ResponseEntity<?> deposit(@RequestBody DepositRequest depositRequest) {
        try {
            Customer updatedCustomer = employeeService.depositMoney(
                depositRequest.getAccountNumber(), 
                depositRequest.getAmount()
            );
            return ResponseEntity.ok(updatedCustomer);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    
    @PostMapping("/withdraw")
    public ResponseEntity<?> withdraw(@RequestBody DepositRequest withdrawRequest) {
        try {
            Customer updatedCustomer = employeeService.withdrawMoney(
            		withdrawRequest.getAccountNumber(), 
            		withdrawRequest.getAmount()
            );
            return ResponseEntity.ok(updatedCustomer);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    
    @GetMapping("/customers")
    public ResponseEntity<Page<Customer>> getAllCustomers(@RequestParam(defaultValue="0") int page,
    		@RequestParam(defaultValue="5") int size
    		) {
    	
        Page<Customer> customers = employeeService.findAllCustomers(PageRequest.of(page, size));
        customers.forEach(customer -> customer.setPassword(null));
        return ResponseEntity.ok(customers);
    }
}
