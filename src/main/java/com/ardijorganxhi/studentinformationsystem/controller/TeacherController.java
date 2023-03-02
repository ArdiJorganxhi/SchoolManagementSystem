package com.ardijorganxhi.studentinformationsystem.controller;


import com.ardijorganxhi.studentinformationsystem.service.TeacherService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/teacher")
@AllArgsConstructor
public class TeacherController {

    private final TeacherService teacherService;
}
