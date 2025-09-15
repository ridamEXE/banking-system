import { Component, OnInit,  Inject, PLATFORM_ID} from '@angular/core';
import { isPlatformBrowser, CommonModule } from '@angular/common';
import { Router } from '@angular/router';
import { EmployeeService } from '../../services/employee.service';

@Component({
  selector: 'app-view-customers',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './view-all-customers.html',
  styleUrls: ['./view-all-customers.css']
})
export class ViewAllCustomersComponent implements OnInit {
  customers: any[] = [];
   currentPage: number = 0;
  pageSize: number = 5;
  totalPages: number = 0;

  constructor(private employeeService: EmployeeService, private router: Router, @Inject(PLATFORM_ID) private platformId: Object) {}

  ngOnInit(): void {
    if (isPlatformBrowser(this.platformId)) {
      this.loadCustomers();
    }
  }

  loadCustomers(): void {
    
  
    this.employeeService.getAllCustomers(this.currentPage, this.pageSize).subscribe(data => {
      this.customers = data.content;
      this.totalPages=data.totalPages;
    });
  }

  goBack(): void {
    this.router.navigate(['/employee-dashboard']);
  }

  
  previousPage(): void {
    if (this.currentPage > 0) {
      this.currentPage--;
      this.loadCustomers();
    }
  }

  nextPage(): void {
    if (this.currentPage + 1 < this.totalPages) {
      this.currentPage++;
      this.loadCustomers();
    }
  }


  // deleteCustomer(customerId: string): void {
  //   const managerPassword = prompt("To confirm deletion, please enter your manager password:");
  //   if (!managerPassword) {
  //     return;
  //   }

  //   const managerId = sessionStorage.getItem('managerId');
  //   if (!managerId) {
  //     alert("Manager session not found. Please log in again.");
  //     return;
  //   }

  //   this.managerService.deleteCustomer(customerId, managerId, managerPassword).subscribe({
  //     next: (response) => {
  //       alert("Customer deleted successfully!");
  //       this.loadCustomers();
  //     },
  //     error: (err) => {
  //       alert(`Error: ${err.error}`);
  //     }
  //   });
  // }

  editCustomer(customer: any): void{
    this.router.navigate(['/edit-customer', customer.customerId], {state: {customerData: customer}});
  }
}