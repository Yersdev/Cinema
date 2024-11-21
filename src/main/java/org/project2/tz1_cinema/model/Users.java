package org.project2.tz1_cinema.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false)
    private String name;
    private String last_name;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false, unique = true)
    @Email(message = "PLease write a valid email")
    private String email;
    @Enumerated(EnumType.STRING)  // Указываем, что роль будет храниться как строка в базе данных
    private Role role;
    @OneToMany(mappedBy = "users", fetch = FetchType.LAZY) // связь с комментариями
    private List<Comment> comments;
}
