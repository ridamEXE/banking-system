import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class RegistrationService {
  private baseUrl = 'http://localhost:8080/api/register';

  constructor(private http: HttpClient) { }

  registerCustomer(registrationData: any): Observable<any> {
    return this.http.post(`${this.baseUrl}/customer`, registrationData, { responseType: 'text' });
  }
}
