package com.ardijorganxhi.studentinformationsystem.controller;


import com.ardijorganxhi.studentinformationsystem.model.Teacher;
import com.ardijorganxhi.studentinformationsystem.service.AuthorizationService;
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

    @GetMapping
    public ResponseEntity<List<Teacher>> getTeachers(HttpServletRequest request){
        authorizationService.getTeacherFromHttpRequest(request);
        return ResponseEntity.ok(teacherService.getTeachers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getTeacherById(@PathVariable Long id, HttpServletRequest request){
        authorizationService.getTeacherFromHttpRequest(request);
        return ResponseEntity.ok(teacherService.getTeacherById(id));
    }
    @DeleteMapping("/{id}")
    public void deleteTeacherById(@PathVariable Long id, HttpServletRequest request){
        authorizationService.getTeacherFromHttpRequest(request);
        teacherService.deleteTeacherById(id);
    }
}
