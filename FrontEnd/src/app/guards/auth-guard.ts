import { inject } from '@angular/core';
import { CanActivateFn, Router } from '@angular/router';
import { PLATFORM_ID } from '@angular/core';
import { isPlatformBrowser } from '@angular/common';

export const authGuard: CanActivateFn = (route, state) => {
  const router = inject(Router);
  const platformId = inject(PLATFORM_ID);

  if (isPlatformBrowser(platformId)) {
    const isLoggedIn = 
      !!sessionStorage.getItem('managerId') || 
      !!sessionStorage.getItem('employeeId') || 
      !!sessionStorage.getItem('customerId');

    if (isLoggedIn) {
      return true; // If logged in, allow access
    }
  }
  
  // If not logged in, redirect to the landing page
  router.navigate(['/']);
  return false;
};



