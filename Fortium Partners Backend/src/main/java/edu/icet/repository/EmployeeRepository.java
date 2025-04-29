package edu.icet.repository;

import edu.icet.entity.EmployeeEntity;
import edu.icet.utils.Department;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<EmployeeEntity,Long> {
    boolean existsByEmail(String email);
    Optional<EmployeeEntity> findByEmail(String email);
    List<EmployeeEntity> findByDepartment(Department department);
}
