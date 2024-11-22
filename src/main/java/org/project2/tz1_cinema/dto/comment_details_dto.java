package org.project2.tz1_cinema.dto;

import lombok.*;
import org.project2.tz1_cinema.model.Movie;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class comment_details_dto {
    private String movie_title;
    private String firstName;          // Имя пользователя
    private String lastName;          // Имя пользователя
    private String email;             // Email пользователя
    private String comments;  // Словарь с фильмами и комментариями

    public comment_details_dto(String firsName, String lastName, String email) {
        this.firstName = firsName;
        this.lastName = lastName;
        this.email = email;
    }

    // Конструкторы, геттеры и сеттеры

    // Добавить комментарий для фильма

}
