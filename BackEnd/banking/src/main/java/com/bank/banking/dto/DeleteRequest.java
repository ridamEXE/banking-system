package com.bank.banking.dto;

import lombok.Data;

@Data
public class DeleteRequest {
    private String managerPassword;

	public String getManagerPassword() {return managerPassword;}

	public void setManagerPassword(String managerPassword) {this.managerPassword = managerPassword;}
}
