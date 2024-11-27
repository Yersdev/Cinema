package org.project2.tz1_cinema.config;

import org.project2.tz1_cinema.model.Role;
import org.project2.tz1_cinema.model.Users;
import org.project2.tz1_cinema.repo.UserRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Set;

@Configuration
public class AdminUserInitializer {

    @Bean
    CommandLineRunner initAdminUser(UserRepo userRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            if (userRepository.findUsersByEmail("admin@gmail.com") == null) {
                Users admin = new Users();
                admin.setName("Admin");
                admin.setEmail("admin@gmail.com");
                admin.setPassword(passwordEncoder.encode("12345678"));
                admin.setRole(Role.ADMIN);
                userRepository.save(admin);
            }
        };
    }
}
