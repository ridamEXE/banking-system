package com.bank.banking.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bank.banking.dto.DeleteRequest;
import com.bank.banking.entity.Customer;
import com.bank.banking.entity.Employee;
import com.bank.banking.service.ManagerService;

@RestController
@RequestMapping("/api/manager")
@CrossOrigin(origins = "http://localhost:4200")
public class ManagerController {

    @Autowired
    private ManagerService managerService;

    @PostMapping("/employees/{managerId}")
    public ResponseEntity<?> registerEmployee(@RequestBody Employee employee, @PathVariable String managerId) {
        try {
            Employee registeredEmployee = managerService.registerEmployee(employee, managerId);
            registeredEmployee.setPassword(null);
            return new ResponseEntity<>(registeredEmployee, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    
    @GetMapping("/employees/{managerId}")
    public ResponseEntity<Page<Employee>> getEmployeesByManager(
    		@PathVariable String managerId,
    		@RequestParam(defaultValue="0") int page,
    		@RequestParam(defaultValue="5") int size
    		) {
        Page<Employee> employees = managerService.findEmployeesByManager(managerId, PageRequest.of(page, size) );
        employees.forEach(employee -> employee.setPassword(null));
        return ResponseEntity.ok(employees);
    }
    
    @PostMapping("/customers/{managerId}")
    public ResponseEntity<?> createCustomerAccount(@RequestBody Customer customer, @PathVariable String managerId) {
        try {
        	System.out.println(managerId);
            Customer createdCustomer = managerService.createCustomerAccount(customer, managerId); 
            System.out.println(customer.getName());
            return new ResponseEntity<>(createdCustomer, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    
    @GetMapping("/customers/{managerId}")
    public ResponseEntity<Page<Customer>> getCustomerByManager(
    		@PathVariable String managerId,
    		@RequestParam(defaultValue="0") int page,
    		@RequestParam(defaultValue="5") int size
    		) {
        Page<Customer> customers = managerService.findCustomerByManager(managerId, PageRequest.of(page, size) );
        customers.forEach(employee -> employee.setPassword(null));
        return ResponseEntity.ok(customers);
    }

    @DeleteMapping("/employees/{employeeId}/{managerId}")
    public ResponseEntity<Map<String, String>> deleteEmployee(@PathVariable String employeeId, @PathVariable String managerId, @RequestBody DeleteRequest deleteRequest) {
        try {
            managerService.deleteEmployee(employeeId, managerId, deleteRequest.getManagerPassword());
            return ResponseEntity.ok(Map.of("message", "Employee with ID " + employeeId + " deleted successfully."));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    @DeleteMapping("/customers/{customerId}/{managerId}")
    public ResponseEntity<Map<String, String>> deleteCustomer(@PathVariable String customerId, @PathVariable String managerId, @RequestBody DeleteRequest deleteRequest) {
        try {
            managerService.deleteCustomer(customerId, managerId, deleteRequest.getManagerPassword());
            return ResponseEntity.ok(Map.of("message","Customer with ID " + customerId + " deleted successfully."));
        } catch (RuntimeException e) {
        
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    @PutMapping("/employees/{employeeId}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable String employeeId, @RequestBody Employee employeeDetails) {
        try {
            Employee updatedEmployee = managerService.updateEmployee(employeeId, employeeDetails);
            return ResponseEntity.ok(updatedEmployee);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/customers/{customerId}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable String customerId, @RequestBody Customer customerDetails) {
        try {
            Customer updatedCustomer = managerService.updateCustomer(customerId, customerDetails);
            return ResponseEntity.ok(updatedCustomer);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}