import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Router, ActivatedRoute } from '@angular/router';
import { ManagerService } from '../../services/manager.service';

@Component({
  selector: 'app-edit-customer',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './edit-customer.html',
  styleUrls: ['../register-employee/register-employee.css']
})
export class EditCustomerComponent implements OnInit {
  customer: any = {};

   successMessage: string = '';
  errorMessage: string = '';
  errors: any={};
  constructor(
    private router: Router,
    private route: ActivatedRoute,
    private managerService: ManagerService
  ) {
    const navigation = this.router.getCurrentNavigation();
    this.customer = navigation?.extras?.state?.['customerData'];
  }

  ngOnInit(): void {
    if (!this.customer) {
      this.goBack();
    }
  }

  onSubmit(): void {
    this.managerService.updateCustomer(this.customer.customerId, this.customer).subscribe({
      next: () => {
        alert('customer updated successfully!');
        this.goBack();
      },
      error: (err) => alert('Failed to update customer.')
    });
  }

  goBack(): void {
    this.router.navigate(['/view-customers']);
  }
}