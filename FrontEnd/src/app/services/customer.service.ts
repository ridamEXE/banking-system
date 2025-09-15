import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CustomerService {
  private baseUrl = 'http://localhost:8080/api/customer';

  constructor(private http: HttpClient) { }

  transferMoney(fromAccountNumber:string, toAccountNumber: string, amount: number, password: string): Observable<any>{
    console.log(fromAccountNumber,toAccountNumber,amount);
    const transferRequest = {
      fromAccountNumber: fromAccountNumber,
      toAccountNumber: toAccountNumber,
      amount: amount,
      password: password
    };
    return this.http.post(`${this.baseUrl}/transfer`, transferRequest);
  }

  withdrawMoney(accountNumber: string, amount: number): Observable<any>{
    return this.http.post(`${this.baseUrl}/withdraw`, {accountNumber, amount});
  }

  resetPassword(requestData: any): Observable<any>{
    return this.http.post(`${this.baseUrl}/reset-password`, requestData, {responseType: 'text'});
  }

  getCustomerDetails(accountNumber: string):Observable<any>{
    return this.http.get(`${this.baseUrl}/details/${accountNumber}`)
  }

  updateCustomerDetails(customerId: string, details: any): Observable<any> {
    return this.http.put(`${this.baseUrl}/update-details/${customerId}`, details);
  }

  getTransactions(accountNumber: string, page: number, size: number): Observable<any> {
    return this.http.get(`${this.baseUrl}/${accountNumber}/statement?page=${page}&size=${size}`);
  }

  viewBalance(accountNumber: string): Observable<any> {
    return this.http.get(`${this.baseUrl}/details/${accountNumber}`);
  }
}
