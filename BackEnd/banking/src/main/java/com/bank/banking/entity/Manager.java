package com.bank.banking.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import org.hibernate.annotations.Where;

@Entity
@Data
@Where(clause = "status = 'active'")
public class Manager {
	@Id
	private String managerId;	//can be like "admin"

	private String password;
	private String status="active";

	public String getManagerId() {return managerId;}
	public String getPassword() {return password;}
	public String getStatus() {return status;}

	public void setManagerId(String managerId) {this.managerId = managerId;}
	public void setPassword(String password) {this.password = password;}
	public void setStatus(String status) {this.status = status;}
	
}
