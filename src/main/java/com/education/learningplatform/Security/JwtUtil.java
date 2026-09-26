package com.education.learningplatform.Security;

import com.education.learningplatform.User.model.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Service
public class JwtUtil {
    private final String secretKey;

    private static final long EXPIRE_DURATION =
        2 * 24 * 60 * 60 * 1000L; // 2 days

    public JwtUtil(@Value("${app.jwt.secret}") String secretKey) {
        this.secretKey = secretKey;
    }

    private SecretKey getSigningKey() {
        return Keys.hmacShaKeyFor(
            secretKey.getBytes(StandardCharsets.UTF_8)
        );
    }

    public String generateToken(Long userId,String email) {

        return Jwts.builder()
            .subject(email)
            .claim("userId", userId)
            .issuedAt(new Date())
            .expiration(
                new Date(
                    System.currentTimeMillis()
                        + EXPIRE_DURATION
                )
            )
            .signWith(getSigningKey())
            .compact();
    }

    public boolean isTokenValid(String token) {

        try {

            Jwts.parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(token);

            return true;

        } catch (Exception exception) {

            return false;
        }
    }

    public String getSubject(String token) {

        return Jwts.parser()
            .verifyWith(getSigningKey())
            .build()
            .parseSignedClaims(token)
            .getPayload()
            .getSubject();
    }

    public static User getCurrentUser() {

        Authentication auth =
            SecurityContextHolder
                .getContext()
                .getAuthentication();

        if (auth == null ||
            !(auth.getPrincipal() instanceof User user)) {

            throw new ResponseStatusException(
                HttpStatus.UNAUTHORIZED,
                "User not authenticated"
            );
        }

        return user;
    }
}
