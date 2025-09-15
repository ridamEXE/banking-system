import { Component, ViewChild } from '@angular/core';
import { CommonModule ,ViewportScroller} from '@angular/common';
import { FormsModule, NgForm } from '@angular/forms';
import { Router } from '@angular/router';
import { RegistrationService } from '../../services/registration.service';

@Component({
  selector: 'app-customer-registration',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './customer-registration.html',
  styleUrls: ['./customer-registration.css']
})
export class CustomerRegistrationComponent {
  @ViewChild('regForm') regForm!: NgForm;
  
  regData = {
    customerSSNId: '',
    accountNumber: '',
    password: ''
  };
  confirmPassword = '';
  successMessage: string = '';
  errorMessage: string = '';

  constructor(private viewportScroller: ViewportScroller,private registrationService: RegistrationService, private router: Router) {}

  onSubmit(): void {
    if (!this.regForm.valid || this.regData.password !== this.confirmPassword) {
      return;
    }

    this.registrationService.registerCustomer(this.regData).subscribe({
      next: (response) => {
        this.successMessage = response;
        this.errorMessage = '';
        this.regForm.resetForm();
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