import { Component, Inject, PLATFORM_ID } from '@angular/core';
import { isPlatformBrowser } from '@angular/common';
import { Router } from '@angular/router';

@Component({
  selector: 'app-employee-dashboard',
  standalone: true,
  imports: [],
  templateUrl: './employee-dashboard.html',
  styleUrls: ['./employee-dashboard.css'] // Reuse styles
})
export class EmployeeDashboardComponent {

  constructor(
    private router: Router,
    @Inject(PLATFORM_ID) private platformId: Object
  ) {}

  goToDeposit(): void {
    this.router.navigate(['/deposit']);
  }

  goToWithdraw(): void {
    this.router.navigate(['/withdraw']);
  }

  goToViewCustomers(): void{
    this.router.navigate(['/view-all-customers']);
  }
  
  logout(): void {
    if (isPlatformBrowser(this.platformId)) {
      sessionStorage.clear(); // Clear all session data
    }
    this.router.navigate(['/login']);
  }
}