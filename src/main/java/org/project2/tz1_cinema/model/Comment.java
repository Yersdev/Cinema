package org.project2.tz1_cinema.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@ToString
@Table(name = "comment_user")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)  // связь с фильмом
    @JsonBackReference
    @JoinColumn(name = "movie_id", nullable = false)  // внешний ключ на фильм
    private Movie movie;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.EAGER)  // связь с пользователем
    @JoinColumn(name = "user_id", nullable = false)  // внешний ключ на пользователя
    private Users users;

    private String comment;
}
