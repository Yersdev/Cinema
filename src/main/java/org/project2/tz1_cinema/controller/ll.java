package org.project2.tz1_cinema.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

public class ll{
    @GetMapping("/user/{id:\\d+}")
    public String getUserById(@PathVariable int id) {
        // Логика обработки запроса
        return "userProfile";
    }

}
