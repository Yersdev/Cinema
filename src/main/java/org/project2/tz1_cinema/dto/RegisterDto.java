package org.project2.tz1_cinema.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RegisterDto {

    @NotEmpty
    private String name;

    @NotEmpty
    private String last_name;

    @NotEmpty
    @Email
    private String email;

    @Size(min = 6, message = "Minimum password length is 6 characters")
    @Pattern(regexp = "^(?=.*[!@#$%^&*(),.?\":{}|<>]).+$", message = "Пароль должен содержать хотя бы один специальный символ.")
    private String password;

    private String confirmPassword;

}
