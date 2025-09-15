import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http'
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ManagerService {
  private baseUrl = 'http://localhost:8080/api/manager';
  constructor(private http: HttpClient) { }
  registerEmployee(employeeData: any, managerId: string): Observable<any>{
    return this.http.post(`${this.baseUrl}/employees/${managerId}`, employeeData);
  }

  getAllEmployees(managerId: string, page: number, size: number): Observable<any>{
    return this.http.get<any[]>(`${this.baseUrl}/employees/${managerId}?page=${page}&size=${size}`);
  }

  createCustomerAccount(customerData: any, managerId: string): Observable<any>{
    console.log(customerData,"20");
    return this.http.post<any[]>(`${this.baseUrl}/customers/${managerId}`, customerData);
  }

  getAllCustomers(managerId: string, page: number, size: number): Observable<any>{
    return this.http.get<any[]>(`${this.baseUrl}/customers/${managerId}?page=${page}&size=${size}`);
  }

  deleteEmployee(employeeId: number, managerId: string, managerPassword: string): Observable<any> {
    const options = {
      body: { managerPassword: managerPassword }
    };
    return this.http.delete(`${this.baseUrl}/employees/${employeeId}/${managerId}`, options);
  }

  deleteCustomer(customerId: string, managerId: string, managerPassword: string): Observable<any> {
    const options = {
      body: { managerPassword: managerPassword }
    };
    return this.http.delete(`${this.baseUrl}/customers/${customerId}/${managerId}`, options);
  }

  updateEmployee(employeeId: number, employeeData: any): Observable<any> {
    return this.http.put(`${this.baseUrl}/employees/${employeeId}`, employeeData);
  }

  updateCustomer(customerId: string, customerData: any): Observable<any> {
    return this.http.put(`${this.baseUrl}/customers/${customerId}`, customerData);
  }
}
