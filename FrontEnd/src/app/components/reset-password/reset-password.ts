import { Component, ViewChild } from '@angular/core';
import { CommonModule,ViewportScroller } from '@angular/common';
import { FormsModule, NgForm } from '@angular/forms';
import { Router } from '@angular/router';
import { CustomerService } from '../../services/customer.service';

@Component({
  selector: 'app-reset-password',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './reset-password.html',
  styleUrls: ['../register-employee/register-employee.css'] // Reuse styles
})
export class ResetPasswordComponent {
  @ViewChild('resetForm') resetForm!: NgForm;
  
  resetData = {
    customerId: '',
    accountNumber: '',
    oldPassword: '',
    newPassword: ''
  };
  successMessage: string = '';
  errorMessage: string = '';

  constructor(private viewportScroller: ViewportScroller,private customerService: CustomerService, private router: Router) {}

  onSubmit(): void {
    if (!this.resetForm.valid) return;

    this.customerService.resetPassword(this.resetData).subscribe({
      next: (response) => {
        this.successMessage = response;
        this.errorMessage = '';
        this.resetForm.resetForm();
                 this.viewportScroller.scrollToPosition([0,0]);

      },
      error: (err) => {
        this.errorMessage = err.error;
        this.successMessage = '';
                 this.viewportScroller.scrollToPosition([0,0]);

      }
    });
  }

  goToLogin(): void {
    this.router.navigate(['/login']);
  }
}