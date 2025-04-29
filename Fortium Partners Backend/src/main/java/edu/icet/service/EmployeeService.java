package edu.icet.service;

import edu.icet.dto.Employee;
import edu.icet.utils.Department;

import java.util.List;

public interface EmployeeService {
    void add(Employee employee);
    Employee searchByID(Long id);
    List<Employee> searchByDepartment(Department department);
    void update(Employee employee);
    void delete(Long id);
    List<Employee> getAll();
}
