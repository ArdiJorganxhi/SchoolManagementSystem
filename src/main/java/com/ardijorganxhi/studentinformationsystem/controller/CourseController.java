package com.ardijorganxhi.studentinformationsystem.controller;

import com.ardijorganxhi.studentinformationsystem.dto.CourseCreationDto;
import com.ardijorganxhi.studentinformationsystem.model.Course;
import com.ardijorganxhi.studentinformationsystem.service.CourseService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/course")
@AllArgsConstructor
public class CourseController {

    private final CourseService courseService;


    @PostMapping("/create")
    public ResponseEntity<Course> createCourse(CourseCreationDto creationDto){
        return ResponseEntity.ok(courseService.createCourse(creationDto));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteCourse(@PathVariable Long id){
         courseService.deleteCourse(id);
         return ResponseEntity.ok("Course is deleted!");
    }

}
