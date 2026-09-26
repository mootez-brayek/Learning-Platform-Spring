package com.education.learningplatform.Security;

import com.education.learningplatform.User.model.User;
import com.education.learningplatform.User.repository.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

@Component
@RequiredArgsConstructor
public class JwtTokenFilter extends OncePerRequestFilter{

    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(
        HttpServletRequest request,
        HttpServletResponse response,
        FilterChain filterChain
    ) throws ServletException, IOException {

        String header = request.getHeader("Authorization");

        // No JWT → continue normally
        if (header == null || !header.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        String token = header.substring(7).trim();

        try {

            if (jwtUtil.isTokenValid(token)) {

                // Get email from JWT
                String email = jwtUtil.getSubject(token);
                // Find user in database
                User user = userRepository
                    .findByEmail(email)
                    .orElse(null);

                if (user != null && user.isActive()) {
                    System.out.println("Authenticated user: " + user.getEmail());
                    System.out.println("Role: " + user.getRole());
                    // Create authentication
                    UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(
                            user,
                            null,
                            List.of(
                                new SimpleGrantedAuthority(
                                    "ROLE_" + user.getRole().name()
                                )
                            )
                        );

                    // Tell Spring Security that this user is authenticated
                    SecurityContextHolder
                        .getContext()
                        .setAuthentication(authentication);
                }
            }

        } catch (Exception exception) {

            SecurityContextHolder.clearContext();
        }
        System.out.println(
            "SecurityContext authentication: "
                + SecurityContextHolder.getContext().getAuthentication()
        );

        filterChain.doFilter(request, response);
    }
}
