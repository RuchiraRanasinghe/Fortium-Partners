package edu.icet.service.impl;

import edu.icet.dto.Employee;
import edu.icet.entity.EmployeeEntity;
import edu.icet.repository.EmployeeRepository;
import edu.icet.service.EmployeeService;
import edu.icet.utils.Department;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    private final ModelMapper mapper;
    private final EmployeeRepository repository;

    @Override
    @Transactional
    public void add(Employee employee) {
        if (repository.existsByEmail(employee.getEmail())) {
            throw new IllegalArgumentException("Email already exists: " + employee.getEmail());
        }

        Date currentTime = new Date();
        employee.setCreatedAt(currentTime);
        employee.setUpdatedAt(currentTime);

        repository.save(mapper.map(employee, EmployeeEntity.class));
    }

    @Override
    public Employee searchByID(Long id) {
        Optional<EmployeeEntity> employeeOptional = repository.findById(id);
        if (employeeOptional.isPresent()) {
            return mapper.map(employeeOptional.get(), Employee.class);
        } else {
            throw new EntityNotFoundException("Employee not found with id: " + id);
        }
    }

    @Override
    public List<Employee> searchByDepartment(Department department) {
        List<EmployeeEntity> employeeEntities = repository.findByDepartment(department);
        return employeeEntities.stream()
                .map(entity -> mapper.map(entity, Employee.class))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void update(Employee employee) {
        if (!repository.existsById(employee.getId())) {
            throw new EntityNotFoundException("Employee not found with id: " + employee.getId());
        }

        Optional<EmployeeEntity> existingWithEmail = repository.findByEmail(employee.getEmail());
        if (existingWithEmail.isPresent() && !existingWithEmail.get().getId().equals(employee.getId())) {
            throw new IllegalArgumentException("Email already in use by another employee: " + employee.getEmail());
        }

        EmployeeEntity existingEmployee = repository.findById(employee.getId()).get();
        employee.setCreatedAt(existingEmployee.getCreatedAt());

        employee.setUpdatedAt(new Date());

        repository.save(mapper.map(employee, EmployeeEntity.class));
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException("Cannot delete - Employee not found with id: " + id);
        }

        repository.deleteById(id);
    }

    @Override
    public List<Employee> getAll() {
        List<EmployeeEntity> all = repository.findAll();
        return all.stream()
                .map(entity -> mapper.map(entity, Employee.class))
                .collect(Collectors.toList());
    }
}
