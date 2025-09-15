import { Component,ViewChild } from '@angular/core';
import { CommonModule, ViewportScroller } from '@angular/common';
import { FormsModule,NgForm } from '@angular/forms';
import { Router } from '@angular/router';
import { ManagerService } from '../../services/manager.service';

@Component({
  selector: 'app-create-customer-account',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './create-customer-account.html',
  styleUrls: ['./create-customer-account.css'] // You can reuse register-employee.css
})
export class CreateCustomerAccountComponent {
  @ViewChild('customerForm') customerForm!: NgForm;
  customer: any = {
    customerSSNId: '',
    name: '',
    email: '',
    dateOfBirth: '',
    contactNumber: '',
    address: '',
    ifscCode: '',
    accountBalance: 0,
    aadharCardNumber: '',
    panCardNo: '',
    gender: 'male',
    maritalStatus: 'single'
  };
  isSsnIdGenerated=false;
  generateSSNId(): void{
    const randomId= Math.floor(10000000 +Math.random()*90000000);
    this.customer.customerSSNId=randomId.toString();
    this.isSsnIdGenerated=true;
  }
  
  successMessage: string = '';
  errorMessage: string = '';

  constructor(
    private managerService: ManagerService, private router: Router,
    private viewportScroller: ViewportScroller
  ) {}

 
  onSubmit(): void {
    const managerId = sessionStorage.getItem('managerId');
    if(!managerId){
      this.errorMessage = 'Manager ID not found. Please log in again.';
      return;
    }

    this.managerService.createCustomerAccount(this.customer, managerId).subscribe(
      response => {
        this.successMessage = `Account created successfully for ${response.name}! New Account Number: ${response.accountNumber}`;
        this.errorMessage = '';
        this.customerForm.resetForm();
        this.viewportScroller.scrollToPosition([0,0]);
        this.isSsnIdGenerated=false;  
      },
      error => {
        this.errorMessage = error.error || 'Account creation failed.';
        this.successMessage = '';
        this.viewportScroller.scrollToPosition([0,0]);
      }
    );
  }

  goBack(): void {
    this.router.navigate(['/manager-dashboard']);
  }
}
