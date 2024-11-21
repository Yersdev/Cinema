//package org.project2.tz1_cinema.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//
//@Configuration
//@EnableWebSecurity
//public class SecurityConfig {
//
//    private final JwtTokenProvider jwtTokenProvider;
//
//    // Конструктор для внедрения зависимости
//    public SecurityConfig(JwtTokenProvider jwtTokenProvider) {
//        this.jwtTokenProvider = jwtTokenProvider;
//    }
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        return http
//                .authorizeRequests(auth -> auth
//                        // Публичные URL, доступные без аутентификации
//                        .requestMatchers("/users", "/users/account/", "/users/account/register", "/login", "/logout").permitAll()
//
//                        // URL для администраторов, требует роль ADMIN
//                        .requestMatchers("/admin/**").hasRole("ADMIN")
//
//                        // URL для пользователей, требует роль USER
//                        .requestMatchers("/user/**").hasRole("USER")
//
//                        // Все остальные URL, требующие аутентификации
//                        .anyRequest().authenticated()
//                )
//                .formLogin(form -> form
//                        .defaultSuccessUrl("/", true)  // Перенаправление на главную страницу после успешного входа
//                )
//                .logout(logout -> logout
//                        .logoutSuccessUrl("/users/")  // Перенаправление на страницу после выхода
//                )
//                .addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider), UsernamePasswordAuthenticationFilter.class) // Добавляем фильтр для JWT
//                .build();
//    }
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//    @Bean
//    public JwtTokenProvider jwtTokenProvider() {
//        return new JwtTokenProvider(); // Создаем бин для JwtTokenProvider
//    }
//}
