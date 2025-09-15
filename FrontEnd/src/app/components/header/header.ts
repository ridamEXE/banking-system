
import { Component, Inject, PLATFORM_ID, OnInit } from '@angular/core';
import { CommonModule, isPlatformBrowser } from '@angular/common';
import { Router, RouterModule } from '@angular/router';

@Component({
  selector: 'app-header',
  standalone: true,
  imports: [CommonModule, RouterModule],
  templateUrl: './header.html',
  styleUrls: ['./header.css']
})
export class HeaderComponent {

  constructor(
    private router: Router,
    @Inject(PLATFORM_ID) private platformId: Object
  ) {}

  isLoggedIn(): boolean {
    if (isPlatformBrowser(this.platformId)) {
      // The user is logged in if any ID is present in session storage
      return !!sessionStorage.getItem('managerId') || 
             !!sessionStorage.getItem('employeeId') || 
             !!sessionStorage.getItem('customerId');
    }
    return false;
  }

  logout(): void {
    if (isPlatformBrowser(this.platformId)) {
      // Clear all session data to end the session
      sessionStorage.clear();
    }
    // Navigate back to the landing page
    this.router.navigate(['/']);
  }
}
