import { Component, OnInit, Inject, PLATFORM_ID } from '@angular/core';
import { CommonModule, isPlatformBrowser } from '@angular/common';
import { Router } from '@angular/router';
import { CustomerService } from '../../services/customer.service';

@Component({
  selector: 'app-account-statement',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './account-statement.html',
  styleUrls: ['./account-statement.css']
})
export class AccountStatementComponent implements OnInit {
  transactions: any[] = [];
  currentPage: number = 0;
  pageSize: number = 5;
  totalPages: number = 0;
  accountNumber: string = '';

  constructor(
    private customerService: CustomerService,
    private router: Router,
    @Inject(PLATFORM_ID) private platformId: Object
  ) {}

  ngOnInit(): void {
    if (isPlatformBrowser(this.platformId)) {
      this.accountNumber = sessionStorage.getItem('accountNumber') || '';
      if (this.accountNumber) {
        this.loadTransactions();
      }
    }
  }

  loadTransactions(): void {
    this.customerService.getTransactions(this.accountNumber, this.currentPage, this.pageSize).subscribe(data => {
      this.transactions = data.content;
      this.totalPages = data.totalPages;
    });
  }

  previousPage(): void {
    if (this.currentPage > 0) {
      this.currentPage--;
      this.loadTransactions();
    }
  }

  nextPage(): void {
    if (this.currentPage + 1 < this.totalPages) {
      this.currentPage++;
      this.loadTransactions();
    }
  }

  goBack(): void {
    this.router.navigate(['/customer-dashboard']);
  }
}
