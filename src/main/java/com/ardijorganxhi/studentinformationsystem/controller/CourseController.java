package com.ardijorganxhi.studentinformationsystem.controller;

import com.ardijorganxhi.studentinformationsystem.dto.CreateCourseDto;
import com.ardijorganxhi.studentinformationsystem.dto.GradeDto;
import com.ardijorganxhi.studentinformationsystem.model.Course;
import com.ardijorganxhi.studentinformationsystem.model.StudentCourse;
import com.ardijorganxhi.studentinformationsystem.model.Teacher;
import com.ardijorganxhi.studentinformationsystem.service.AuthorizationService;
import com.ardijorganxhi.studentinformationsystem.service.CourseService;
import com.ardijorganxhi.studentinformationsystem.service.GradeService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/course")
@AllArgsConstructor
public class CourseController {

    private final CourseService courseService;
    private final GradeService gradeService;
    private final AuthorizationService authorizationService;


    @PostMapping
    public ResponseEntity<Course> createCourse(@RequestBody CreateCourseDto courseDto, HttpServletRequest request){
        Teacher teacher = authorizationService.getTeacherFromHttpRequest(request);
        return ResponseEntity.ok(courseService.createCourse(courseDto, teacher));
    }

    @PutMapping("/{courseId}/student/{studentId}/grade")
    public ResponseEntity<StudentCourse> gradeStudent(@PathVariable Long courseId, @PathVariable Long studentId, @RequestBody GradeDto gradeDto, HttpServletRequest request) throws Exception {
        authorizationService.getTeacherFromHttpRequest(request);
        return ResponseEntity.ok(gradeService.gradeStudent(studentId, courseId, gradeDto));
    }


}
