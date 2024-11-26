package org.project2.tz1_cinema.controller;

import org.project2.tz1_cinema.dto.LoginRequestDTO;
import org.project2.tz1_cinema.dto.RegisterDTO;
import org.project2.tz1_cinema.model.Users;
import org.project2.tz1_cinema.repo.UserRepo;
import org.project2.tz1_cinema.service.UserService;
import org.project2.tz1_cinema.util.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private UserRepo userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<Users> registerUser(@Valid @RequestBody RegisterDTO userRegistrationDTO) {
        Users createdUser = userService.registerUser(userRegistrationDTO);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }
    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@Valid @RequestBody LoginRequestDTO loginRequestDTO) {
        Users user = userRepository.findUsersByEmail(loginRequestDTO.getEmail());

        if (user == null || !passwordEncoder.matches(loginRequestDTO.getPassword(), user.getPassword())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email or password");
        }

        String token = jwtTokenProvider.generateToken(user.getEmail());
        return ResponseEntity.ok(token);
    }
}
