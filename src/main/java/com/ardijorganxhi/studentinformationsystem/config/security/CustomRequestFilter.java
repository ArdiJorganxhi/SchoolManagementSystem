package com.ardijorganxhi.studentinformationsystem.config.security;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import com.ardijorganxhi.studentinformationsystem.service.UserService;
import com.ardijorganxhi.studentinformationsystem.model.User;
import io.jsonwebtoken.ExpiredJwtException;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@AllArgsConstructor
@Component
public class CustomRequestFilter extends OncePerRequestFilter {

    private final JwtTokenUtility jwtTokenUtility;
    private final UserService userService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, javax.servlet.http.HttpServletResponse response, javax.servlet.FilterChain filterChain) throws IOException, ServletException {

        final String requestTokenHeader = request.getHeader("Authorization");

        String username = null;
        String jwtToken = null;

        if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
            jwtToken = requestTokenHeader.substring(7);
            try {
                username = jwtTokenUtility.getUsernameFromToken(jwtToken);
            } catch (IllegalArgumentException e) {
                logger.error("Token Parse Error!");
            } catch (ExpiredJwtException e) {
                logger.error("Expired Token");
            } catch (Exception e) {
                logger.error("Invalid token");
            }
        }

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            try {
                User user = userService.findByEmail(username);

                if (jwtTokenUtility.validateToken(jwtToken, user)) {
                    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                            user, user.getAuthorities());
                    usernamePasswordAuthenticationToken
                            .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                    logger.info("jwt(" + username + ") -> authenticated(true)");
                }
            } catch (Exception e) {
                logger.error("jwt(" + username + ") -> authenticated(false) \n\t - Exception:" + e.getMessage());
            }
        }
        filterChain.doFilter(request, response);

    }
}
