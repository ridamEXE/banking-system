package com.bank.banking.dto;

import lombok.Data;

@Data
public class LoginRequest {
	private String role;	//manager, employee, customer
	
	// UNIVERSAL FIELD
	private String password;
	
	// SPECIFIC ID
	private String managerId;
	private String employeeId;
	private String customerId;
	
	// FOR CUSTOMER LOGIN
	private String accountNumber;

	public String getRole() {return role;}
	public String getPassword() {return password;}
	public String getManagerId() {return managerId;}
	public String getEmployeeId() {return employeeId;}
	public String getCustomerId() {return customerId;}
	public String getAccountNumber() {return accountNumber;}
	
	public void setRole(String role) {this.role = role;}
	public void setPassword(String password) {this.password = password;}
	public void setManagerId(String managerId) {this.managerId = managerId;}
	public void setEmployeeId(String employeeId) {this.employeeId = employeeId;}
	public void setCustomerId(String customerId) {this.customerId = customerId;}
	public void setAccountNumber(String accountNumber) {this.accountNumber = accountNumber;}
}
