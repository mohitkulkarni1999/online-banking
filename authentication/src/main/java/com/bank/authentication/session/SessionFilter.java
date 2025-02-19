package com.bank.authentication.session;

import com.bank.authentication.service.SessionService;
import com.bank.authentication.util.JwtUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.time.LocalDateTime;

public class SessionFilter extends OncePerRequestFilter {
    @Autowired
    private SessionService sessionService;

    @Autowired
    private JwtUtils jwtUtils;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String token = jwtUtils.getJwtFromHeader(request); // Extract token from request header

        if (token != null && jwtUtils.validateJwtToken(token)) {
            sessionService.getUserIdByToken(token).ifPresent(userId -> {
                sessionService.getUserEmailByToken(token).ifPresent(email -> {
                    UserThreadLocalContext.setUserSession(new UserSession(userId, email));
                });
            });
        }

        try {
            filterChain.doFilter(request, response);
        } finally {
            UserThreadLocalContext.clear();
        }
    }

}
