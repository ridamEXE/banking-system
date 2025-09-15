import { Component, Inject, PLATFORM_ID } from '@angular/core';
import { AuthService } from '../../services/auth.service';
import { Router } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { isPlatformBrowser, CommonModule } from '@angular/common';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [FormsModule, CommonModule],
  templateUrl: './login.html',
  styleUrls: ['./login.css']
})
export class LoginComponent {
  role: string = 'customer'; 
  credentials = {
    managerId: '',
    employeeId: null,
    customerId: '',
    accountNumber: '',
    password: ''
  };
  errorMessage: string = '';

  constructor(private authService: AuthService, private router: Router, @Inject(PLATFORM_ID) private platformId: Object) {}

  onSubmit(): void {
    const loginRequest = {
      role: this.role,
      ...this.credentials
    };

    this.authService.login(loginRequest).subscribe(
      response => {
        console.log('Login successful', response);
        this.errorMessage = '';
        switch(response.role) {
          case 'manager':
            if (isPlatformBrowser(this.platformId)) {
              sessionStorage.setItem('managerId', this.credentials.managerId);
            }
            this.router.navigate(['/manager-dashboard']);
            break;
          case 'employee':
            if (isPlatformBrowser(this.platformId)) {
              sessionStorage.setItem('employeeId', this.credentials.employeeId!);
            }
            this.router.navigate(['/employee-dashboard']);
            break;
          case 'customer':
             if (isPlatformBrowser(this.platformId)) {
              sessionStorage.setItem('customerId', this.credentials.customerId!.toString());
              sessionStorage.setItem('accountNumber', this.credentials.accountNumber!.toString());

            }
            this.router.navigate(['/customer-dashboard']);
            break;
        }
      },
      error => {
        console.error('Login failed', error);
        this.errorMessage = error.error.message || 'Login failed. Please check your credentials.';
      }
    );
  }
}