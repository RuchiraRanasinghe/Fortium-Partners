package edu.icet.service.impl;

import edu.icet.dto.Employee;
import edu.icet.dto.User;
import edu.icet.entity.UserEntity;
import edu.icet.repository.UserRepository;
import edu.icet.service.EmployeeService;
import edu.icet.service.UserService;
import edu.icet.utils.Department;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final EmployeeService employeeService;
    private final ModelMapper mapper;

    @Override
    @Transactional
    public void register(User user) {
        if (user.getDepartment() != Department.HR) {
            throw new IllegalArgumentException("Only HR employees can register.");
        }

        if (userRepository.existsByEmail(user.getEmail())) {
            throw new IllegalArgumentException("Email already exists.");
        }

        user.setCreatedAt(new Date());
        user.setUpdatedAt(new Date());

        // Save in users table
        userRepository.save(mapper.map(user, UserEntity.class));

        // Also save in employees table (exclude password)
        Employee employee = mapper.map(user, Employee.class);
        employeeService.add(employee);
    }

    @Override
    public User login(String email, String password) {
        UserEntity user = userRepository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        if (!user.getPassword().equals(password)) {
            throw new IllegalArgumentException("Invalid credentials");
        }

        if (user.getDepartment() != Department.HR) {
            throw new IllegalArgumentException("Only HR employees can log in");
        }

        return mapper.map(user, User.class);
    }
}
