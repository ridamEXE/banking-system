package com.bank.banking.entity;

import java.time.LocalDate;

import org.hibernate.annotations.Where;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
@Where(clause = "status = 'active'")

public class Customer {
	@Id
	private String customerId;
	
	private String customerSSNId;
	private String name;
	private String email;
	private String password ="";
	private String address;
	private String contactNumber;
	private String accountNumber;
	private String ifscCode;
	private String accountBalance;
	private String aadharCardNumber;
	private String panCardNo;
	private LocalDate dateOfBirth;
	private String gender;
	private String martialStatus;
	private String createdByManagerId;
	private String status="active";
	
	public String getCustomerId() {return customerId;}
	public String getCustomerSSNId() {return customerSSNId;}
	public String getName() {return name;}
	public String getEmail() {return email;}
	public String getPassword() {return password;}
	public String getAddress() {return address;}
	public String getContactNumber() {return contactNumber;}
	public String getAccountNumber() {return accountNumber;}
	public String getIfscCode() {return ifscCode;}
	public String getAccountBalance() {return accountBalance;}
	public String getAadharCardNumber() {return aadharCardNumber;}
	public String getPanCardNo() {return panCardNo;}
	public LocalDate getDateOfBirth() {return dateOfBirth;}
	public String getGender() {return gender;}
	public String getMartialStatus() {return martialStatus;}
	public String getCreatedByManagerId() {return createdByManagerId;}
	public String getStatus() {return status;}
	
	public void setCustomerId(String customerId) {this.customerId = customerId;}
	public void setCustomerSSNId(String customerSSNId) {this.customerSSNId = customerSSNId;}
	public void setName(String name) {this.name = name;}
	public void setEmail(String email) {this.email = email;}
	public void setPassword(String password) {this.password = password;}
	public void setAddress(String address) {this.address = address;}
	public void setContactNumber(String contactNumber) {this.contactNumber = contactNumber;}
	public void setAccountNumber(String accountNumber) {this.accountNumber = accountNumber;}
	public void setIfscCode(String ifscCode) {this.ifscCode = ifscCode;}
	public void setAccountBalance(String accountBalance) {this.accountBalance = accountBalance;}
	public void setAadharCardNumber(String aadharCardNumber) {this.aadharCardNumber = aadharCardNumber;}
	public void setPanCardNo(String panCardNo) {this.panCardNo = panCardNo;}
	public void setDateOfBirth(LocalDate dateOfBirth) {this.dateOfBirth = dateOfBirth;}
	public void setGender(String gender) {this.gender = gender;}
	public void setMartialStatus(String martialStatus) {this.martialStatus = martialStatus;}
	public void setCreatedByManagerId(String createdByManagerId) {this.createdByManagerId = createdByManagerId;}
	public void setStatus(String status) {this.status = status;}
}
