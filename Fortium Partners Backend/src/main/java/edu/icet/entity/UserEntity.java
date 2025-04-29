package edu.icet.entity;

import edu.icet.utils.Department;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class UserEntity {
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
    private String password;
    @NotNull(message = "Department cannot be empty")
    private Department department;
    private Date createdAt;
    private Date updatedAt;
}
