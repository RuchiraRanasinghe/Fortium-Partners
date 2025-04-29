package edu.icet.dto;

import edu.icet.utils.Department;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
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
    private Department department = Department.HR;
    private Date createdAt;
    private Date updatedAt;
}
