package com.bank.banking.dto;

import lombok.Data;

@Data
public class CustomerRegistrationRequest {
    private String customerSSNId;
    private String accountNumber;
    private String password;
	
    public String getCustomerSSNId() {return customerSSNId;}
	public String getAccountNumber() {return accountNumber;}
	public String getPassword() {return password;}
	
	public void setCustomerSSNId(String customerSSNId) {this.customerSSNId = customerSSNId;}
	public void setAccountNumber(String accountNumber) {this.accountNumber = accountNumber;}
	public void setPassword(String password) {this.password = password;}
    
    
}