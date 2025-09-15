package com.bank.banking.service;

import java.time.LocalDate;
import java.time.Period;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bank.banking.entity.Customer;
import com.bank.banking.entity.Employee;
import com.bank.banking.entity.Manager;
import com.bank.banking.repository.CustomerRepository;
import com.bank.banking.repository.EmployeeRepository;
import com.bank.banking.repository.ManagerRepository;

@Service
public class ManagerService {

    @Autowired
    private EmployeeRepository employeeRepository;
    
    @Autowired
    private CustomerRepository customerRepository;
    
    @Autowired
    private ManagerRepository managerRepository;


    public Employee registerEmployee(Employee employee, String managerId) {
    	System.out.println(employee.getEmployeeId());
    	
    	String newEmployeeId;
        do {
            // Generate a random 8-digit number and convert it to a String
            int randomId = 10000000 + new Random().nextInt(90000000);
            newEmployeeId = String.valueOf(randomId);
        } while (employeeRepository.existsById(newEmployeeId));
        
//        if (employeeRepository.existsById(employee.getEmployeeId())) {
//            throw new RuntimeException("Employee with ID " + employee.getEmployeeId() + " already exists.");
//        }
        
        if(employee.getDateOfBirth() != null) {
        	if(Period.between(employee.getDateOfBirth(), LocalDate.now()).getYears() < 18) {
        		throw new RuntimeException("Employee must be at least 18 years old");
        	}
        }
        else {
        	throw new RuntimeException("Date of Birth is Required.");
        }
        
        employee.setEmployeeId(newEmployeeId);
        employee.setCreatedByManagerId(managerId);
        
        return employeeRepository.save(employee);
    }
    
    public Page<Employee> findEmployeesByManager(String managerId, Pageable pageable) {
        return employeeRepository.findByCreatedByManagerId(managerId, pageable);
    }
    
    public Customer createCustomerAccount(Customer customer, String managerId) {
        if (customerRepository.findByCustomerSSNId(customer.getCustomerSSNId()).isPresent()) {
            throw new RuntimeException("Customer with this SSN ID already has an account.");
        }

        if (customer.getDateOfBirth() != null) {
            if (Period.between(customer.getDateOfBirth(), LocalDate.now()).getYears() < 18) {
                throw new RuntimeException("Customer must be at least 18 years old.");
            }
        } else {
            throw new RuntimeException("Date of Birth is required.");
        }
        
        customer.setCreatedByManagerId(managerId);
        customer.setAccountNumber(generateUniqueAccountNumber());
        customer.setCustomerId("CUST" + (100000 + new Random().nextInt(900000)));
        
        

        return customerRepository.save(customer);
    }
    
    public Page<Customer> findCustomerByManager(String managerId, Pageable pageable){
    	return customerRepository.findByCreatedByManagerId(managerId, pageable);
    }
    
    private String generateUniqueAccountNumber() {
        Random random = new Random();
        String accountNumber;
        do {
            accountNumber = String.format("%013d", random.nextLong(1_000_000_000_0000L));
        } while (customerRepository.findByAccountNumber(accountNumber).isPresent());
        return accountNumber;
    }

    @Transactional
    public void deleteEmployee(String employeeId, String managerId, String managerPassword) {
        Manager manager = managerRepository.findById(managerId)
                .orElseThrow(() -> new RuntimeException("Manager not found."));

        if (!manager.getPassword().equals(managerPassword)) {
            throw new RuntimeException("Incorrect manager password.");
        }

        if (!employeeRepository.existsById(employeeId)) {
            throw new RuntimeException("Employee with ID " + employeeId + " not found.");
        }
        Employee employee= employeeRepository.findById(employeeId)
                .orElseThrow(() -> new RuntimeException("Employee not found."));
        employee.setStatus("inactive");
        employeeRepository.save(employee);
    }

    @Transactional
    public void deleteCustomer(String customerId, String managerId, String managerPassword) {
        Manager manager = managerRepository.findById(managerId)
                .orElseThrow(() -> new RuntimeException("Manager not found."));

        if (!manager.getPassword().equals(managerPassword)) {
            throw new RuntimeException("Incorrect manager password.");
        }

        if (!customerRepository.existsById(customerId)) {
            throw new RuntimeException("Customer with ID " + customerId + " not found.");
        }
        Customer customer= customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found."));
        customer.setStatus("inactive");
        customerRepository.save(customer);

    }

    @Transactional
    public Employee updateEmployee(String employeeId, Employee employeeDetails) {
        Employee existingEmployee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new RuntimeException("Employee not found with id: " + employeeId));
        
        if(existingEmployee.getDateOfBirth() != null) {
        	if(Period.between(existingEmployee.getDateOfBirth(), LocalDate.now()).getYears() < 18) {
        		throw new RuntimeException("Employee must be at least 18 years old");
        	}
        }
        else {
        	throw new RuntimeException("Date of Birth is Required.");
        }

        existingEmployee.setFirstName(employeeDetails.getFirstName());
        existingEmployee.setLastName(employeeDetails.getLastName());
        existingEmployee.setEmail(employeeDetails.getEmail());
        existingEmployee.setDateOfBirth(employeeDetails.getDateOfBirth());
        existingEmployee.setDepartment(employeeDetails.getDepartment());
        existingEmployee.setAddress(employeeDetails.getAddress());

        return employeeRepository.save(existingEmployee);
    }
    
    @Transactional
    public Customer updateCustomer(String customerId, Customer customerDetails) {
        Customer existingCustomer = customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found with id: " + customerId));

        
        
        existingCustomer.setName(customerDetails.getName());
        existingCustomer.setEmail(customerDetails.getEmail());
        existingCustomer.setAddress(customerDetails.getAddress());
        existingCustomer.setContactNumber(customerDetails.getContactNumber());
        existingCustomer.setIfscCode(customerDetails.getIfscCode());
        existingCustomer.setAadharCardNumber(customerDetails.getAadharCardNumber());
        existingCustomer.setPanCardNo(customerDetails.getPanCardNo());
        existingCustomer.setDateOfBirth(customerDetails.getDateOfBirth());
        existingCustomer.setGender(customerDetails.getGender());
        existingCustomer.setMartialStatus(customerDetails.getMartialStatus());
        existingCustomer.setAccountBalance(customerDetails.getAccountBalance());

        return customerRepository.save(existingCustomer);
    }
}