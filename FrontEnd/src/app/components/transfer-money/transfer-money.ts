import { Component, OnInit, Inject, PLATFORM_ID, ViewChild } from '@angular/core';
import { isPlatformBrowser, CommonModule } from '@angular/common';
import { FormsModule, NgForm } from '@angular/forms';
import { Router } from '@angular/router';
import { CustomerService } from '../../services/customer.service';

@Component({
  selector: 'app-transfer-money',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './transfer-money.html',
  styleUrls: ['../register-employee/register-employee.css']
})
export class TransferMoneyComponent implements OnInit {
  @ViewChild('transferForm') transferForm!: NgForm;
  
  transferData = {
    fromAccountNumber: '',
    toAccountNumber: '',
    amount: null,
    password: ''
  };
  successMessage: string = '';
  errorMessage: string = '';

  constructor(
    private customerService: CustomerService, 
    private router: Router,
    @Inject(PLATFORM_ID) private platformId: Object
  ) {}
  
  ngOnInit(): void {
    if (isPlatformBrowser(this.platformId)) {
      this.transferData.fromAccountNumber = sessionStorage.getItem('accountNumber') || '';
    }
  }

  onSubmit(): void {
    if (!this.transferForm.valid) return;

    if(this.transferData.fromAccountNumber===this.transferData.toAccountNumber){
      alert("Sorry! Sender's and Reciever's Account cannot be same")
      return;
    }

    this.customerService.transferMoney(
      this.transferData.fromAccountNumber,
      this.transferData.toAccountNumber,
      this.transferData.amount!,
      this.transferData.password
    ).subscribe({
      next: (response) => {
        
        console.log(response,"err",response["message"])
        this.successMessage = response.message;
        this.errorMessage = '';
        this.transferForm.resetForm();
      },
      error: (err) => {
        console.log(err,"err",err.error)
        this.errorMessage = err.error.error;
        this.successMessage = '';
      }
    });
  }

  goBack(): void {
    this.router.navigate(['/customer-dashboard']);
  }
}