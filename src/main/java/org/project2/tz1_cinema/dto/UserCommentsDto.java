package org.project2.tz1_cinema.dto;

import java.util.HashMap;
import java.util.Map;

public class UserCommentsDto {
    private String username;          // Имя пользователя
    private String email;             // Email пользователя
    private Map<String, String> comments;  // Словарь с фильмами и комментариями

    // Конструкторы, геттеры и сеттеры
    public UserCommentsDto(String username, String email) {
        this.username = username;
        this.email = email;
        this.comments = new HashMap<>();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Map<String, String> getComments() {
        return comments;
    }

    public void setComments(Map<String, String> comments) {
        this.comments = comments;
    }

    // Добавить комментарий для фильма
    public void addComment(String movieTitle, String comment) {
        this.comments.put(movieTitle, comment);
    }
}
