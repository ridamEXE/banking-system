package com.bank.banking.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import org.hibernate.annotations.Where;

@Entity
@Data
@Where(clause = "status = 'active'")
public class Employee {
	@Id
	private String employeeId;	//minimum 8 length
	
	
	private String firstName;
	private String lastName;
	private String password;
	private String email;
	private LocalDate dateOfBirth;
	private String department;
	private String address;
	private String createdByManagerId;
	private String status="active";
	
	public String getEmployeeId() {return employeeId;}
	public String getFirstName() {return firstName;}
	public String getLastName() {return lastName;}
	public String getPassword() {return password;}
	public String getEmail() {return email;}
	public LocalDate getDateOfBirth() {return dateOfBirth;}
	public String getDepartment() {return department;}
	public String getAddress() {return address;}
	public String getCreatedByManagerId() {return createdByManagerId;}
	public String getStatus() {return status;}
	
	public void setEmployeeId(String employeeId) {this.employeeId = employeeId;}
	public void setFirstName(String firstName) {this.firstName = firstName;}
	public void setLastName(String lastName) {this.lastName = lastName;}
	public void setPassword(String password) {this.password = password;}
	public void setEmail(String email) {this.email = email;}
	public void setDateOfBirth(LocalDate dateOfBirth) {this.dateOfBirth = dateOfBirth;}
	public void setDepartment(String department) {this.department = department;}
	public void setAddress(String address) {this.address = address;}
	public void setCreatedByManagerId(String createdByManagerId) {this.createdByManagerId = createdByManagerId;}
	public void setStatus(String status) {this.status = status;}
}
