//package org.project2.tz1_cinema.config;
//
//import io.jsonwebtoken.ExpiredJwtException;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import javax.servlet.Filter;
//import javax.servlet.FilterChain;
//import javax.servlet.FilterConfig;
//import javax.servlet.ServletException;
//import javax.servlet.ServletRequest;
//import javax.servlet.ServletResponse;
//import javax.servlet.http.HttpServletRequest;
//import java.io.IOException;
//
//public class JwtAuthenticationFilter extends OncePerRequestFilter {
//
//    private final JwtTokenProvider jwtTokenProvider;
//
//    public JwtAuthenticationFilter(JwtTokenProvider jwtTokenProvider) {
//        this.jwtTokenProvider = jwtTokenProvider;
//    }
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest request, javax.servlet.http.HttpServletResponse response, FilterChain filterChain)
//            throws ServletException, IOException {
//
//        // Извлекаем токен из заголовка Authorization
//        String token = getTokenFromRequest(request);
//
//        // Проверяем токен
//        if (token != null && jwtTokenProvider.validateToken(token)) {
//            String username = jwtTokenProvider.getEmailFromToken(token);  // Извлекаем email из токена
//            UsernamePasswordAuthenticationToken authentication =
//                    new UsernamePasswordAuthenticationToken(username, null, null); // Создаем аутентификацию
//
//            SecurityContextHolder.getContext().setAuthentication(authentication);  // Устанавливаем аутентификацию в контекст безопасности
//        }
//
//        filterChain.doFilter(request, response);  // Продолжаем фильтрацию
//    }
//
//    private String getTokenFromRequest(HttpServletRequest request) {
//        String bearerToken = request.getHeader("Authorization");
//        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
//            return bearerToken.substring(7);  // Извлекаем токен после "Bearer "
//        }
//        return null;
//    }
//}
//
