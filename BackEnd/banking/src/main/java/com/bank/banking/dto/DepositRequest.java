package com.bank.banking.dto;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class DepositRequest {
    private String accountNumber;
    private BigDecimal amount;
	
    public String getAccountNumber() {return accountNumber;}
	public BigDecimal getAmount() {return amount;}
	
	public void setAccountNumber(String accountNumber) {this.accountNumber = accountNumber;}
	public void setAmount(BigDecimal amount) {this.amount = amount;}
    
}
