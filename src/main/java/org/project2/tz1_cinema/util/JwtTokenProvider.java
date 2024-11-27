package org.project2.tz1_cinema.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component("jwtTokenProvider") // Используем уникальное имя для компонента
public class JwtTokenProvider {

    private static final String SECRET_KEY = "yourSecretKeyHere";
    private static final long EXPIRATION_TIME = 3600000; // 1 час

    // Метод для генерации JWT токена для пользователя
    public String generateToken(String username) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + EXPIRATION_TIME);

        return Jwts.builder()
                .setSubject(username)                // Устанавливаем предмет (обычно это имя пользователя)
                .setIssuedAt(now)                    // Устанавливаем время создания токена
                .setExpiration(expiryDate)           // Устанавливаем срок действия токена
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY) // Подписываем токен с использованием секретного ключа
                .compact();                          // Собираем токен в строку
    }

    // Метод для получения имени пользователя из токена
    public String getUsernameFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    // Метод для проверки валидности токена
    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token);
            return true; // Токен действителен
        } catch (Exception e) {
            return false; // Токен недействителен
        }
    }
}
