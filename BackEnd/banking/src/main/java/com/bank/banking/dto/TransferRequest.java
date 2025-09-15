package com.bank.banking.dto;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class TransferRequest {
    private String fromAccountNumber;
    private String toAccountNumber;
    private BigDecimal amount;
    private String password;
	public String getFromAccountNumber() {
		return fromAccountNumber;
	}
	public String getToAccountNumber() {
		return toAccountNumber;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void setFromAccountNumber(String fromAccountNumber) {
		this.fromAccountNumber = fromAccountNumber;
	}
	public void setToAccountNumber(String toAccountNumber) {
		this.toAccountNumber = toAccountNumber;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
}