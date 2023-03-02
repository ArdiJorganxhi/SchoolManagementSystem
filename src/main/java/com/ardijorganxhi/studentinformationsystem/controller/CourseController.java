package com.ardijorganxhi.studentinformationsystem.controller;

import com.ardijorganxhi.studentinformationsystem.service.CourseService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/course")
@AllArgsConstructor
public class CourseController {

    private final CourseService courseService;




}
