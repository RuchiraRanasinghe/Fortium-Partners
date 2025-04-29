package edu.icet.controller;

import edu.icet.dto.Employee;
import edu.icet.service.EmployeeService;
import edu.icet.utils.Department;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
@RequiredArgsConstructor
@CrossOrigin
public class EmployeeController {
    private final EmployeeService service;

    @PostMapping("/add")
    public void add(@RequestBody Employee employee){service.add(employee);}

    @GetMapping("/searchById/{id}")
    public Employee searchById(@PathVariable Long id){return service.searchByID(id);}

    @GetMapping("/searchByDepartment/{department}")
    public List<Employee> searchByDepartment(@PathVariable Department department){return service.searchByDepartment(department);}

    @PutMapping("/update")
    public void update(@RequestBody Employee employee){service.update(employee);}

    @DeleteMapping("/delete")
    public void delete(Long id){service.delete(id);}

    @GetMapping("/all")
    public List<Employee> getAll(){return service.getAll();}
}
