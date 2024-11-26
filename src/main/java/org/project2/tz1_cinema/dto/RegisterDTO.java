package org.project2.tz1_cinema.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class RegisterDTO {

    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Last name is required")
    private String lastName;

    @NotBlank(message = "Password is required")
    @Pattern(regexp = "^(?=.*[!@#$%^&*(),.?\":{}|<>]).+$", message = "Пароль должен содержать хотя бы один специальный символ.")

    private String password;

    @NotBlank(message = "Email is required")
    @Email(message = "Please write a valid email")
    private String email;
}
