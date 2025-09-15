
import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { EmployeeService } from '../../services/employee.service';

@Component({
  selector: 'app-deposit-money',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './deposit-money.html',
  styleUrls: ['./deposit-money.css'] // Reuse styles
})
export class DepositMoneyComponent {
  depositData = {
    accountNumber: '',
    amount: null
  };
  successMessage: string = '';
  errorMessage: string = '';
  errors: any={};

  constructor(private employeeService: EmployeeService, private router: Router) {}

  onSubmit(): void {
    
    this.employeeService.depositMoney(this.depositData.accountNumber, this.depositData.amount!).subscribe({
      next: (response) => {
        this.successMessage = `Successfully deposited ₹${this.depositData.amount} to account ${response.accountNumber}. New balance is ₹${response.accountBalance}.`;
        this.errorMessage = '';
        this.depositData = { accountNumber: '', amount: null }; // Reset form
      },
      error: (err) => {
        this.errorMessage = err.error;
        this.successMessage = '';
      }
    });
  }

  goBack(): void {
    this.router.navigate(['/employee-dashboard']);
  }
}