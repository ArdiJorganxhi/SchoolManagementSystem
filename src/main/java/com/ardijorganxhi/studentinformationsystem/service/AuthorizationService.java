package com.ardijorganxhi.studentinformationsystem.service;

import com.ardijorganxhi.studentinformationsystem.config.security.JwtTokenUtility;
import com.ardijorganxhi.studentinformationsystem.model.Student;
import com.ardijorganxhi.studentinformationsystem.model.Teacher;
import com.ardijorganxhi.studentinformationsystem.model.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
@AllArgsConstructor
public class AuthorizationService {

    private final UserService userService;
    private final StudentService studentService;
    private final TeacherService teacherService;

    private final JwtTokenUtility jwtTokenUtility;

    public Long getUserIdFromHttpRequest(HttpServletRequest request) {
        return getUserFromHttpRequest(request).getId();
    }
    public Long getStudentIdFromHttpRequest(HttpServletRequest request) {
        return getStudentFromHttpRequest(request).getId();
    }
    public Long getTeacherIdFromHttpRequest(HttpServletRequest request) {
        return getTeacherFromHttpRequest(request).getId();
    }

    public User getUserFromHttpRequest(HttpServletRequest request) {
        final String token = request.getHeader("Authorization").substring(7);
        return userService.findByEmail(jwtTokenUtility.getUsernameFromToken(token));
    }

    public Student getStudentFromHttpRequest(HttpServletRequest request) {
        final String token = request.getHeader("Authorization").substring(7);
        return studentService.findByEmail(jwtTokenUtility.getUsernameFromToken(token));
    }

    public Teacher getTeacherFromHttpRequest(HttpServletRequest request) {
        final String token = request.getHeader("Authorization").substring(7);
        return teacherService.findByEmail(jwtTokenUtility.getUsernameFromToken(token));
    }


}
