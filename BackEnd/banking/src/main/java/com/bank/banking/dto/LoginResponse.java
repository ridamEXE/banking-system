package com.bank.banking.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginResponse {
	private boolean success;
	private String message;
	private String role;
	
	public LoginResponse(boolean success, String message, String role) {
		this.success = success;
		this.message = message;
		this.role = role;
	}
	
	public boolean isSuccess() {return success;}
	public String getMessage() {return message;}
	public String getRole() {return role;}
	
	public void setSuccess(boolean success) {this.success = success;}
	public void setMessage(String message) {this.message = message;}
	public void setRole(String role) {this.role = role;}
}