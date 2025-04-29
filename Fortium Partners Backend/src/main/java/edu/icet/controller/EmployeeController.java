package edu.icet.controller;

import edu.icet.dto.Employee;
import edu.icet.service.EmployeeService;
import edu.icet.utils.Department;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.PrintWriter;
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

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id){service.delete(id);}

    @GetMapping("/all")
    public List<Employee> getAll(){return service.getAll();}

    @GetMapping(value = "/export", produces = "text/csv")
    public void exportCSV(HttpServletResponse response) throws IOException {
        response.setContentType("text/csv");
        response.setHeader("Content-Disposition", "attachment; filename=employees.csv");

        List<Employee> employees = service.getAll(); // Calls your existing service method
        PrintWriter writer = response.getWriter();

        // CSV header
        writer.println("ID,Name,Email,Department,Created At,Updated At");

        // CSV rows
        for (Employee emp : employees) {
            writer.println(String.format("%d,%s,%s,%s,%s,%s",
                    emp.getId(),
                    emp.getName(),
                    emp.getEmail(),
                    emp.getDepartment(),
                    emp.getCreatedAt(),
                    emp.getUpdatedAt()
            ));
        }

        writer.flush();
        writer.close();
    }
}
