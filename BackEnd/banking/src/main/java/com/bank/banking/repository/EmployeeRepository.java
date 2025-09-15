package com.bank.banking.repository;
import com.bank.banking.entity.Employee;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, String>{
	Page<Employee> findByCreatedByManagerId(String managerId, Pageable pageable);
}