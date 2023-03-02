package com.ardijorganxhi.studentinformationsystem.controller;


import com.ardijorganxhi.studentinformationsystem.dto.LoginDto;
import com.ardijorganxhi.studentinformationsystem.dto.RegistrationDto;
import com.ardijorganxhi.studentinformationsystem.model.User;
import com.ardijorganxhi.studentinformationsystem.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthController {

    private final AuthService authService;


    @PostMapping("/student/register")
    public ResponseEntity<User> registerStudent(@RequestBody RegistrationDto registrationDto){

        return ResponseEntity.ok(authService.registerStudent(registrationDto));
    }

    @PostMapping("/teacher/register")
    public ResponseEntity<User> registerTeacher(@RequestBody RegistrationDto registrationDto){
        return ResponseEntity.ok(authService.registerTeacher(registrationDto));
    }



    @PostMapping("/login")
    public ResponseEntity<String> loginStudent(@RequestBody LoginDto loginDto) throws Exception{
        return ResponseEntity.ok(authService.loginStudent(loginDto));
    }



}
