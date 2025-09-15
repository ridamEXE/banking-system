package com.bank.banking.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bank.banking.dto.LoginRequest;
import com.bank.banking.dto.LoginResponse;
import com.bank.banking.entity.Customer;
import com.bank.banking.entity.Employee;
import com.bank.banking.entity.Manager;
import com.bank.banking.repository.CustomerRepository;
import com.bank.banking.repository.EmployeeRepository;
import com.bank.banking.repository.ManagerRepository;

@Service
public class AuthService {
	@Autowired
	private ManagerRepository managerRepository;
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Autowired
	private CustomerRepository customerRepository;
	
	public LoginResponse login(LoginRequest request) {
		String roleString = request.getRole();
		
		switch(roleString.toLowerCase()) {
			case "manager":
				return loginManager(request);
			case "employee":
				return loginEmployee(request);
			case "customer":
				return loginCustomer(request);
			default:
				return new LoginResponse(false, "Invalid role specified.", null);
		}
	}
	private LoginResponse loginManager(LoginRequest request) {
        Optional<Manager> managerOpt = managerRepository.findById(request.getManagerId());
        if (managerOpt.isPresent() && managerOpt.get().getPassword().equals(request.getPassword())) {
            return new LoginResponse(true, "Manager login successful!", "manager");
        }
        return new LoginResponse(false, "Invalid Manager ID or Password.", "manager");
    }

    private LoginResponse loginEmployee(LoginRequest request) {
        Optional<Employee> employeeOpt = employeeRepository.findById(request.getEmployeeId());
        if (employeeOpt.isPresent() && employeeOpt.get().getPassword().equals(request.getPassword())) {
            return new LoginResponse(true, "Employee login successful!", "employee");
        }
        return new LoginResponse(false, "Invalid Employee ID or Password.", "employee");
    }

    private LoginResponse loginCustomer(LoginRequest request) {
    	System.out.println(request.getCustomerId());
    	System.out.println(request.getAccountNumber());
        Optional<Customer> customerOpt = customerRepository.findByCustomerIdAndAccountNumber(request.getCustomerId(), request.getAccountNumber());
        
        if (customerOpt.isPresent() && customerOpt.get().getPassword().equals("")) {
            return new LoginResponse(false, "You need to register first before login", "customer");
        }
        else if (customerOpt.isPresent() && customerOpt.get().getPassword().equals(request.getPassword())) {
            return new LoginResponse(true, "Customer login successful!", "customer");
        }
        return new LoginResponse(false, "Invalid Customer ID, Account Number, or Password.", "customer");
    }
	
}

