import { Component, OnInit } from '@angular/core';
import { Employee, Department } from '../../models/employee.model';
import { EmployeeService } from '../../services/employee.service';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-main-page',
  imports: [FormsModule, CommonModule],
  templateUrl: './main-page.component.html',
  styleUrl: './main-page.component.css'
})
export class MainPageComponent implements OnInit {
  employees: Employee[] = [];
  departments = Object.values(Department);

  searchId: number | undefined;
  searchDepartment: Department | undefined;

  newEmployee: Employee = {
    name: '',
    email: '',
    department: Department.IT
  };

  editing = false;

  constructor(private employeeService: EmployeeService) {}

  ngOnInit(): void {
    this.fetchAll();
  }

  fetchAll(): void {
    this.employeeService.getAll().subscribe(
      (data) => this.employees = data,
      (error) => console.error(error)
    );
  }

  searchById(): void {
    if (this.searchId != null) {
      this.employeeService.getById(this.searchId).subscribe(
        (employee) => this.employees = [employee],
        (error) => console.error(error)
      );
    }
  }

  searchByDepartment(): void {
    if (this.searchDepartment) {
      this.employeeService.getByDepartment(this.searchDepartment).subscribe(
        (data) => this.employees = data,
        (error) => console.error(error)
      );
    }
  }

  saveEmployee(): void {
    if (this.editing && this.newEmployee.id) {
      this.employeeService.update(this.newEmployee).subscribe(
        () => {
          this.fetchAll();
          this.resetForm();
        },
        (error) => console.error(error)
      );
    } else {
      this.employeeService.create(this.newEmployee).subscribe(
        () => {
          this.fetchAll();
          this.resetForm();
        },
        (error) => console.error(error)
      );
    }
  }

  editEmployee(employee: Employee): void {
    this.newEmployee = { ...employee };
    this.editing = true;
  }

  deleteEmployee(id?: number): void {
    if (id != null) {
      this.employeeService.delete(id).subscribe(
        () => this.fetchAll(),
        (error) => console.error(error)
      );
    }
  }

  resetForm(): void {
    this.newEmployee = {
      name: '',
      email: '',
      department: Department.IT
    };
    this.editing = false;
  }
}
