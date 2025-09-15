package com.bank.banking.dto;

import lombok.Data;

@Data
public class ResetPasswordRequest {
	
	private String customerId;
	private String accountNumber;
	private String oldPassword;
	private String newPassword;
	
	public String getCustomerId() {return customerId;}
	public String getAccountNumber() {return accountNumber;}
	public String getOldPassword() {return oldPassword;}
	public String getNewPassword() {return newPassword;}
	
	public void setCustomerId(String customerId) {this.customerId = customerId;}
	public void setAccountNumber(String accountNumber) {this.accountNumber = accountNumber;}
	public void setOldPassword(String oldPassword) {this.oldPassword = oldPassword;}
	public void setNewPassword(String newPassword) {this.newPassword = newPassword;}
	
}
