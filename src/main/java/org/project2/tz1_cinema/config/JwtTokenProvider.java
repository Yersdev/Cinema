//package org.project2.tz1_cinema.config;
//
//import io.jsonwebtoken.*;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Component;
//
//import java.util.Date;
//
//@Component
//public class JwtTokenProvider {
//
//    @Value("${jwt.secret}")  // Секрет для подписи токенов
//    private String secretKey;
//
//    private static final long EXPIRATION_TIME = 86400000L;  // Время жизни токена (1 день)
//
//    // Генерация JWT токена
//    public String generateToken(String email) {
//        return Jwts.builder()
//                .setSubject(email)  // Email сохраняется как "subject"
//                .setIssuedAt(new Date())  // Время создания токена
//                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))  // Время истечения токена
//                .signWith(SignatureAlgorithm.HS512, secretKey)  // Подписываем токен с помощью алгоритма HS512
//                .compact();
//    }
//
//    // Извлечение email из токена
//    public String getEmailFromToken(String token) {
//        Claims claims = Jwts.parser()
//                .setSigningKey(secretKey)  // Устанавливаем секретный ключ для парсинга
//                .build().parseClaimsJws(token)  // Парсим JWT токен
//                .getBody();  // Получаем тело токена (Claims)
//
//        return claims.getSubject();  // Извлекаем email (subject) из тела токена
//    }
//
//    // Проверка токена на валидность
//    public boolean validateToken(String token) {
//        try {
//            // Попытка распарсить токен с секретным ключом
//            Jwts.parser()
//                    .setSigningKey(secretKey)
//                    .build().parseClaimsJws(token);  // Если парсинг успешен, токен валиден
//            return true;
//        } catch (SignatureException | MalformedJwtException | ExpiredJwtException
//                 | UnsupportedJwtException | IllegalArgumentException e) {
//            // В случае ошибок (неверный токен, истекший токен, и т. д.) токен считается недействительным
//            return false;
//        }
//    }
//}
