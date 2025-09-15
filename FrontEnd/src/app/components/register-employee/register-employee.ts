import { Component, OnInit, ViewChild } from '@angular/core';
import { ManagerService } from '../../services/manager.service';
import { Router } from '@angular/router';
import { FormsModule, FormBuilder, FormGroup, Validators, NgForm } from '@angular/forms';
import { CommonModule,ViewportScroller } from '@angular/common';
// import { validateForm } from '../../utils/validations';

@Component({
  selector: 'app-register-employee',
  templateUrl: './register-employee.html',
  styleUrls: ['./register-employee.css'],
  imports: [CommonModule, FormsModule]
})
export class RegisterEmployeeComponent {
  @ViewChild('employeeForm') employeeForm!: NgForm;

  employee = {
    employeeId: null,
    firstName: '',
    lastName: '',
    email: '',
    password: '',
    dateOfBirth: '',
    department: '',
    address: ''
  };
  successMessage: string = '';
  errorMessage: string = '';
  errors: any = {};
  today: string;
  constructor(private managerService: ManagerService, private router: Router,
     private viewportScroller: ViewportScroller
  ) { this.today = new Date().toISOString().split('T')[0]; }


  onSubmit(): void {

    // this.errors=validateForm(this.employee);
    // if(Object.keys(this.errors).length > 0){
    //   return;
    // }

    const managerId = sessionStorage.getItem('managerId');
    if (!managerId) {
      this.errorMessage = `Manager ID not found. Please log in again.`;
      return;
    }

    this.managerService.registerEmployee(this.employee, managerId).subscribe(
      response => {
        this.successMessage = `Employee ${response.firstName} registered successfully! with Employee ID ${response.employeeId}`;
        this.errorMessage = '';
        this.employeeForm.resetForm();
      
        this.viewportScroller.scrollToPosition([0,0]);
      },
      error => {
        this.errorMessage = error.error || 'Registration failed. Please try again.';
        this.successMessage = '';
        this.viewportScroller.scrollToPosition([0,0]);
      }
    );
  }

  goBack(): void {
    this.router.navigate(['/manager-dashboard']);
  }
}
