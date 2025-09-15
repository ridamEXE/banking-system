import { Component, OnInit, Inject, PLATFORM_ID } from '@angular/core';
import { ManagerService } from '../../services/manager.service';
import { Router } from '@angular/router';
import { isPlatformBrowser, CommonModule,ViewportScroller } from '@angular/common';

@Component({
  selector: 'app-view-employees',
  standalone: true,
  imports: [CommonModule], // Import CommonModule for *ngFor
  templateUrl: './view-employees.html',
  styleUrls: ['./view-employees.css']
})
export class ViewEmployeesComponent implements OnInit {
  employees: any[] = [];
  currentPage: number = 0;
  pageSize: number = 5;
  totalPages: number = 0;

  constructor(private viewportScroller: ViewportScroller,private managerService: ManagerService, private router: Router, @Inject(PLATFORM_ID) private platformId: Object) {}

  ngOnInit(): void {
    if (isPlatformBrowser(this.platformId)) {
      this.loadEmployees();
    }
  }

  loadEmployees(): void {
    // Get the manager's ID from session storage
    const managerId = sessionStorage.getItem('managerId');
    console.log("loadEmp")
    if (!managerId) {
      console.error('Manager ID not found in session storage.');
      return;
    }

    this.managerService.getAllEmployees(managerId, this.currentPage, this.pageSize).subscribe(data => {
        console.log("getla")
      this.employees = data.content;
      this.totalPages = data.totalPages;
    });
  }

  goBack(): void {
    this.router.navigate(['/manager-dashboard']);
  }

  previousPage(): void {
    if (this.currentPage > 0) {
      this.currentPage--;
      this.loadEmployees();
    }
  }

  nextPage(): void {
    if (this.currentPage + 1 < this.totalPages) {
      this.currentPage++;
      this.loadEmployees();
    }
  }

  deleteEmployee(employeeId: number): void {
    const managerPassword = prompt("To confirm deletion, please enter your manager password:");
    if (!managerPassword) {
      return;
    }

    const managerId = sessionStorage.getItem('managerId');
    if (!managerId) {
      alert("Manager session not found. Please log in again.");
      return;
    }

    this.managerService.deleteEmployee(employeeId, managerId, managerPassword).subscribe({
      next: (response) => {

        this.loadEmployees();
        alert("Employee deleted successfully!");
      },
      error: (err) => {
        alert(`Error: ${err.error.error}`);
      }
    });
  }

  editEmployee(employee: any): void{
    this.router.navigate(['/edit-employee', employee.employeeId], {state: {employeeData: employee}});
  }
}