package com.bank.banking.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name="account_transactions")
@Data
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String accountNumber;
    private LocalDateTime timestamp;
    private String type; // e.g., "DEPOSIT", "WITHDRAWAL", "TRANSFER_DEBIT", "TRANSFER_CREDIT"
    private BigDecimal amount;
    private String description;
//	private String status="active";
	
    public Long getId() {return id;}
	public String getAccountNumber() {return accountNumber;}
	public LocalDateTime getTimestamp() {return timestamp;}
	public String getType() {return type;}
	public BigDecimal getAmount() {return amount;}
	public String getDescription() {return description;}
//	public String getStatus() {return status;}
	
	public void setId(Long id) {this.id = id;}
	public void setAccountNumber(String accountNumber) {this.accountNumber = accountNumber;}
	public void setTimestamp(LocalDateTime timestamp) {this.timestamp = timestamp;}
	public void setType(String type) {this.type = type;}
	public void setAmount(BigDecimal amount) {this.amount = amount;}
	public void setDescription(String description) {this.description = description;}
//	public void setStatus(String status) {this.status = status;}
}