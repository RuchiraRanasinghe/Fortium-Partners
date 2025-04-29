package edu.icet.entity;

import edu.icet.utils.Department;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "employees")
public class EmployeeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Name cannot be empty")
    @Size(max = 100, message = "Name must not exceed 100 characters")
    @Pattern(regexp = "^[a-zA-Z\\s]+$", message = "Name must contain only alphabetic characters and spaces")
    private String name;
    @NotBlank(message = "Email cannot be empty")
    @Email(message = "Email must be in valid format")
    private String email;
    @NotNull(message = "Department cannot be empty")
    @Enumerated(EnumType.STRING)
    private Department department;
    private Date createdAt;
    private Date updatedAt;
}
