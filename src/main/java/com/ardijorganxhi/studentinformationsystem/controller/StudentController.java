package com.ardijorganxhi.studentinformationsystem.controller;

import com.ardijorganxhi.studentinformationsystem.dto.UserDto;
import com.ardijorganxhi.studentinformationsystem.model.User;
import com.ardijorganxhi.studentinformationsystem.service.AuthorizationService;
import com.ardijorganxhi.studentinformationsystem.service.StudentService;
import com.ardijorganxhi.studentinformationsystem.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/student")
@AllArgsConstructor
public class StudentController {

    private final StudentService studentService;
    private final AuthorizationService authorizationService;






}
