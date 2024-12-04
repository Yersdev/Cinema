package org.project2.tz1_cinema.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.project2.tz1_cinema.dto.RegisterDto;
import org.project2.tz1_cinema.model.Role;
import org.project2.tz1_cinema.model.Users;
import org.project2.tz1_cinema.repo.UserRepo;
import org.project2.tz1_cinema.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
@AllArgsConstructor
@Slf4j
public class AccountController {

    private final UserRepo userRepo;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody RegisterDto registerDto, BindingResult bindingResult) {
        log.info("Registering user: {}", registerDto);

        if (!registerDto.getPassword().equals(registerDto.getConfirmPassword())) {
            bindingResult.addError(new FieldError("registerDto", "confirmPassword", "Пароли не совпадают"));
        }

        if (userRepo.findByEmail(registerDto.getEmail()).isPresent()) {
            bindingResult.addError(new FieldError("registerDto", "email", "Email уже используется"));
        }

        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(bindingResult.getAllErrors());
        }

        try {
            // Преобразуем DTO в объект User и сохраняем нового пользователя
            userRepo.save(convertDTOtoUser(registerDto));

            return ResponseEntity.status(HttpStatus.CREATED).body("Пользователь зарегистрирован успешно!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ошибка при регистрации: " + e.getMessage());
        }
    }

    // Конвертация DTO в модель пользователя
    private Users convertDTOtoUser(RegisterDto registerDto) {
        Users users = new Users();
        users.setName(registerDto.getName());
        users.setLast_name(registerDto.getLast_name());
        users.setEmail(registerDto.getEmail());
        // Шифруем пароль
        users.setPassword(passwordEncoder.encode(registerDto.getPassword()));
        // Устанавливаем роль пользователя
        users.setRole(Role.USER);
        return users;
    }
}
