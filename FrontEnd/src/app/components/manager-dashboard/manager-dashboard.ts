import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-manager-dashboard',
  templateUrl: './manager-dashboard.html',
  styleUrls: ['./manager-dashboard.css']
})
export class ManagerDashboardComponent {

  constructor(private router: Router) {}

  goToRegisterEmployee(): void {
    this.router.navigate(['/register-employee']);
  }

  goToViewEmployees(): void{
    this.router.navigate(['/view-employees']);
  }

  goToCreateCustomerAccount(): void {
    this.router.navigate(['/create-customer-account']);
  }

  goToViewCustomers(): void{
    this.router.navigate(['/view-customers']);
  }
}