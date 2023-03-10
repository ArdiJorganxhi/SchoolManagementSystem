package com.ardijorganxhi.studentinformationsystem.config.security;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import com.ardijorganxhi.studentinformationsystem.model.Student;
import com.ardijorganxhi.studentinformationsystem.model.Teacher;
import com.ardijorganxhi.studentinformationsystem.service.StudentService;
import com.ardijorganxhi.studentinformationsystem.service.TeacherService;
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
    private final StudentService studentService;
    private final TeacherService teacherService;

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
                Student student = studentService.findByEmail(username);
                Teacher teacher = teacherService.findByEmail(username);

                if (jwtTokenUtility.validateTokenForTeacher(jwtToken, teacher)) {
                    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                            teacher, teacher.getAuthorities());
                    usernamePasswordAuthenticationToken
                            .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                    logger.info("jwt(" + username + ") -> authenticated(true)");
                }
                if (jwtTokenUtility.validateTokenForStudent(jwtToken, student)) {
                    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                            student, student.getAuthorities());
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
