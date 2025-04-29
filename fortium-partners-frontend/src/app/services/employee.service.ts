import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Employee, Department } from '../models/employee.model';

@Injectable({
  providedIn: 'root'
})
export class EmployeeService {
  private baseUrl = 'http://localhost:8081/employees';

  constructor(private http: HttpClient) { }

  getAll(): Observable<Employee[]> {
    return this.http.get<Employee[]>(`${this.baseUrl}/all`);
  }

  getById(id: number): Observable<Employee> {
    return this.http.get<Employee>(`${this.baseUrl}/searchById/${id}`);
  }

  getByDepartment(department: Department): Observable<Employee[]> {
    return this.http.get<Employee[]>(`${this.baseUrl}/searchByDepartment/${department}`);
  }

  create(employee: Employee): Observable<any> {
    return this.http.post(`${this.baseUrl}/add`, employee);
  }

  update(employee: Employee): Observable<any> {
    return this.http.put(`${this.baseUrl}/update`, employee);
  }

  delete(id: number): Observable<any> {
    return this.http.delete(`${this.baseUrl}/delete/${id}`);
  }

  exportCSV(): Observable<Blob> {
    return this.http.get(`${this.baseUrl}/export`, { responseType: 'blob' });
  }  
}