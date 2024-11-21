//package org.project2.tz1_cinema.controller;
//
//import jakarta.validation.Valid;
//import org.project2.tz1_cinema.dto.RegisterDto;
//import org.project2.tz1_cinema.model.Role;
//import org.project2.tz1_cinema.model.Users;
//import org.project2.tz1_cinema.repo.UserRepo;
//import org.project2.tz1_cinema.service.UserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.validation.BindingResult;
//import org.springframework.validation.FieldError;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.Optional;
//
//@RestController
//@RequestMapping("/users/account")
//public class AccountController {
//
//    private final UserRepo userRepo;
//    private final UserService userService;
//    private final BCryptPasswordEncoder bCryptPasswordEncoder;
//
//    @Autowired
//    public AccountController(UserRepo userRepo, UserService userService, BCryptPasswordEncoder bCryptPasswordEncoder) {
//        this.userRepo = userRepo;
//        this.userService = userService;
//        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
//    }
//
//    // Метод для регистрации пользователя
//    @PostMapping("/register")
//    public ResponseEntity<?> register(@Valid @RequestBody RegisterDto registerDto, BindingResult bindingResult) {
//
//        // Проверка на совпадение пароля и подтверждения пароля
//        if (!registerDto.getPassword().equals(registerDto.getConfirmPassword())) {
//            bindingResult.addError(new FieldError("registerDto", "confirmPassword", "Пароли не совпадают"));
//        }
//
//        // Проверка, что email не используется
////        if (userService.findByUsername(registerDto.getEmail())) {
////            bindingResult.addError(new FieldError("registerDto", "email", "Email уже используется"));
////        }
//
//        // Если есть ошибки валидации, возвращаем их
//        if (bindingResult.hasErrors()) {
//            return ResponseEntity.badRequest().body(bindingResult.getAllErrors());
//        }
//
//        try {
//            // Преобразуем DTO в объект User
//            Users user = convertDTOtoUser(registerDto);
//            // Сохраняем нового пользователя
//            userRepo.save(user);
//
//            return ResponseEntity.status(HttpStatus.CREATED).body("Пользователь зарегистрирован успешно!");
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ошибка при регистрации: " + e.getMessage());
//        }
//    }
//
//    // Конвертация DTO в модель пользователя
//    private Users convertDTOtoUser(RegisterDto registerDto) {
//        Users users = new Users();
//        users.setName(registerDto.getName());
//        users.setLast_name(registerDto.getLast_name());
//        users.setEmail(registerDto.getEmail());
//        // Шифруем пароль
//        users.setPassword(bCryptPasswordEncoder.encode(registerDto.getPassword()));
//        // Устанавливаем роль пользователя
//        users.setRole(Role.USER);
//        return users;
//    }
//}
