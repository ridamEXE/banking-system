import { Component, Inject, PLATFORM_ID } from '@angular/core';
import { isPlatformBrowser } from '@angular/common';
import { Router } from '@angular/router';

@Component({
  selector: 'app-employee-dashboard',
  standalone: true,
  imports: [],
  templateUrl: './customer-dashboard.html',
  styleUrls: ['../manager-dashboard/manager-dashboard.css'] // Reuse styles
})
export class CustomerDashboardComponent {

  constructor(
    private router: Router,
    @Inject(PLATFORM_ID) private platformId: Object
  ) {}

  // goToDeposit(): void {
  //   this.router.navigate(['/deposit']);
  // }

  goToTransfer(): void {
    this.router.navigate(['/transfer']);
  }

  goToUpdateDetails(): void {
    this.router.navigate(['/update-details'],{state:{}});
  }

  goToAccountStatement(): void {
    this.router.navigate(['/account-statement']);
  }

  goToViewBalance(): void{
    this.router.navigate(['/view-balance']);
  }



  logout(): void {
    if (isPlatformBrowser(this.platformId)) {
      sessionStorage.clear(); // Clear all session data
    }
    this.router.navigate(['/login']);
  }
}