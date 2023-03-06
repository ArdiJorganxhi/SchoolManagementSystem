package com.ardijorganxhi.studentinformationsystem.controller;


import com.ardijorganxhi.studentinformationsystem.dto.CourseDto;
import com.ardijorganxhi.studentinformationsystem.dto.TeacherDto;
import com.ardijorganxhi.studentinformationsystem.service.AuthorizationService;
import com.ardijorganxhi.studentinformationsystem.service.CourseService;
import com.ardijorganxhi.studentinformationsystem.service.TeacherService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/teacher")
@AllArgsConstructor
public class TeacherController {

    private final TeacherService teacherService;
    private final AuthorizationService authorizationService;
    private final CourseService courseService;

    @GetMapping
    public ResponseEntity<List<TeacherDto>> getTeachers(HttpServletRequest request){
        authorizationService.getTeacherFromHttpRequest(request);
        return ResponseEntity.ok(teacherService.getTeachers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TeacherDto> getTeacherById(@PathVariable Long id, HttpServletRequest request){
        authorizationService.getTeacherFromHttpRequest(request);
        return ResponseEntity.ok(teacherService.getTeacherById(id));
    }
    @DeleteMapping("/{id}")
    public void deleteTeacherById(@PathVariable Long id, HttpServletRequest request){
        authorizationService.getTeacherFromHttpRequest(request);
        teacherService.deleteTeacherById(id);
    }

    @GetMapping("/{id}/courses")
    public ResponseEntity<List<CourseDto>> getCoursesByTeacher(@PathVariable Long id, HttpServletRequest request){
        authorizationService.getTeacherFromHttpRequest(request);
        return ResponseEntity.ok(courseService.getCoursesByTeacher(id));
    }
}
