import { Component, OnInit, ViewChild, Inject, PLATFORM_ID } from '@angular/core';
import { CommonModule, isPlatformBrowser,ViewportScroller } from '@angular/common';
import { FormsModule, NgForm } from '@angular/forms';
import { Router } from '@angular/router';
import { CustomerService } from '../../services/customer.service';

@Component({
  selector: 'app-update-details',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './update-details.html',
  styleUrls: ['../register-employee/register-employee.css']
})
export class UpdateDetailsComponent implements OnInit {
  @ViewChild('updateForm') updateForm!: NgForm;
  
  details = {
    email: '',
    dateOfBirth: '',
    address: '',
    maritalStatus: '',
    password: ''
  };
  successMessage: string = '';
  errorMessage: string = '';
  customerId: string | null = null;

  constructor(
    private customerService: CustomerService, 
    private router: Router,
     private viewportScroller: ViewportScroller,
    @Inject(PLATFORM_ID) private platformId: Object
  ) {}

  ngOnInit(): void {
    if(isPlatformBrowser(this.platformId)){
      this.customerId = sessionStorage.getItem('customerId');
      const accountNumber = sessionStorage.getItem('accountNumber');

      if(accountNumber){
        this.customerService.getCustomerDetails(accountNumber).subscribe(data => {
          this.details.email = data.email;
          this.details.dateOfBirth = data.dateOfBirth;
          this.details.address = data.address;
          this.details.maritalStatus = data.maritalStatus;
        });
      }
    }
  }

  onSubmit(): void {
    if (!this.updateForm.valid) return;

    if (isPlatformBrowser(this.platformId)) {
      const customerId = sessionStorage.getItem('customerId');
      if (!customerId) {
        this.errorMessage = "Session expired. Please log in again.";
        return;
      }

      this.customerService.updateCustomerDetails(customerId, this.details).subscribe({
        next: (response) => {
          this.successMessage = "Details updated successfully!";
          this.errorMessage = '';
          this.updateForm.resetForm();
        this.viewportScroller.scrollToPosition([0,0]);
        },
        error: (err) => {
          this.errorMessage = err.error;
          this.successMessage = '';
        
        this.viewportScroller.scrollToPosition([0,0]);
        }
      });
    }
  }

  goBack(): void {
    this.router.navigate(['/customer-dashboard']);
  }
}