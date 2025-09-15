import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class EmployeeService {
  private baseUrl = 'http://localhost:8080/api/employee';

  constructor(private http: HttpClient) { }

  depositMoney(accountNumber: string, amount: number): Observable<any>{
    return this.http.post(`${this.baseUrl}/deposit`, {accountNumber, amount});
  }

  withdrawMoney(accountNumber: string, amount: number): Observable<any>{
    return this.http.post(`${this.baseUrl}/withdraw`, {accountNumber, amount});
  }
  getAllCustomers( page: number, size: number): Observable<any>{
    return this.http.get<any[]>(`${this.baseUrl}/customers?page=${page}&size=${size}`);
  }
}
