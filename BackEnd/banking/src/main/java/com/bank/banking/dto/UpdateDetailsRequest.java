package com.bank.banking.dto;

import java.time.LocalDate;
import lombok.Data;

@Data
public class UpdateDetailsRequest {
    private String address;
    private String email;
    private String maritalStatus;
    private LocalDate dateOfBirth;
    private String password;
    
	public String getAddress() {return address;}
	public String getEmail() {return email;}
	public String getMaritalStatus() {return maritalStatus;}
	public LocalDate getDateOfBirth() {return dateOfBirth;}
	public String getPassword() {return password;}
	
	public void setAddress(String address) {this.address = address;}
	public void setEmail(String email) {this.email = email;}
	public void setMaritalStatus(String maritalStatus) {this.maritalStatus = maritalStatus;}
	public void setDateOfBirth(LocalDate dateOfBirth) {this.dateOfBirth = dateOfBirth;}
	public void setPassword(String password) {this.password = password;}
}
