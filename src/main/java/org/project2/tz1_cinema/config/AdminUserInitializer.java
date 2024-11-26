package org.project2.tz1_cinema.config;

import org.project2.tz1_cinema.model.Role;
import org.project2.tz1_cinema.model.Users;
import org.project2.tz1_cinema.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class AdminUserInitializer {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Bean
    CommandLineRunner initAdminUser(UserRepo userRepository) {
        return args -> {
            if (userRepository.findUsersByEmail("admin@example.com") == null) {
                Users admin = new Users();
                admin.setName("Admin");
                admin.setLast_name("User");
                admin.setEmail("admin@example.com");
                admin.setPassword(passwordEncoder.encode("adminpassword"));
                admin.setRole(Role.ADMIN);
                userRepository.save(admin);

                System.out.println("Admin user created with email: admin@example.com and password: adminpassword");
            }
        };
    }
}
