import { Component, OnInit, Inject, PLATFORM_ID, ViewChild } from '@angular/core';
import { isPlatformBrowser, CommonModule } from '@angular/common';
import { FormsModule, NgForm } from '@angular/forms';
import { Router } from '@angular/router';
import { CustomerService } from '../../services/customer.service';

@Component({
  selector: 'app-transfer-money',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './view-balance.html',
  styleUrls: ['./view-balance.css']
})
export class ViewBalanceComponent implements OnInit {
  @ViewChild('transferForm') transferForm!: NgForm;
  
  transferData = {
    fromAccountNumber: '',
    
  };
  successMessage: string = '';
  errorMessage: string = '';
  accountBalance:string='';

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

    this.customerService.viewBalance(
      this.transferData.fromAccountNumber,
     
    ).subscribe({
      next: (response) => {
        this.successMessage = response;
        this.accountBalance=response.accountBalance;

        console.log(this.successMessage);
        this.errorMessage = '';
        this.transferForm.resetForm();
      },
      error: (err) => {
        this.errorMessage = err.error;
        this.successMessage = '';
      }
    });
  }

  goBack(): void {
    this.router.navigate(['/customer-dashboard']);
  }
}