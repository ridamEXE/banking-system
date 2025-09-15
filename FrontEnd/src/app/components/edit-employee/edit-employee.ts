import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Router, ActivatedRoute } from '@angular/router';
import { ManagerService } from '../../services/manager.service';

@Component({
  selector: 'app-edit-employee',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './edit-employee.html',
  styleUrls: ['../register-employee/register-employee.css']
})
export class EditEmployeeComponent implements OnInit {
  employee: any = {};

   successMessage: string = '';
  errorMessage: string = '';
  errors: any={};

  constructor(
    private router: Router,
    private route: ActivatedRoute,
    private managerService: ManagerService
  ) {
    const navigation = this.router.getCurrentNavigation();
    this.employee = navigation?.extras?.state?.['employeeData'];
  }

  ngOnInit(): void {
    if (!this.employee) {
      this.goBack();
    }
  }

  onSubmit(): void {
    this.managerService.updateEmployee(this.employee.employeeId, this.employee).subscribe({
      next: () => {
        alert('Employee updated successfully!');
        this.goBack();
      },
      error: (err) => alert('Failed to update employee.')
    });
  }

  goBack(): void {
    this.router.navigate(['/view-employees']);
  }
}