
import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { EmployeeService } from '../../services/employee.service';

@Component({
  selector: 'app-deposit-money',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './withdraw-money.html',
  styleUrls: ['./withdraw-money.css'] // Reuse styles
})
export class WithdrawMoneyComponent {
  depositData = {
    accountNumber: '',
    amount: null
  };
  successMessage: string = '';
  errorMessage: string = '';
  errors: any={};

  constructor(private employeeService: EmployeeService, private router: Router) {}

  onSubmit(): void {
    
    this.employeeService.withdrawMoney(this.depositData.accountNumber, this.depositData.amount!).subscribe({
      next: (response) => {
        this.successMessage = `Successfully withdrawn ₹${this.depositData.amount} from account ${response.accountNumber}. New balance is ₹${response.accountBalance}.`;
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