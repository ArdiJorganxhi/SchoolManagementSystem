package com.ardijorganxhi.studentinformationsystem.controller;

import com.ardijorganxhi.studentinformationsystem.dto.StudentCourseDto;
import com.ardijorganxhi.studentinformationsystem.dto.StudentDto;
import com.ardijorganxhi.studentinformationsystem.model.Student;
import com.ardijorganxhi.studentinformationsystem.model.StudentCourse;
import com.ardijorganxhi.studentinformationsystem.service.AuthorizationService;
import com.ardijorganxhi.studentinformationsystem.service.CourseService;
import com.ardijorganxhi.studentinformationsystem.service.StudentService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/student")
@AllArgsConstructor
public class StudentController {

    private final StudentService studentService;
    private final AuthorizationService authorizationService;

    private final CourseService courseService;



    @GetMapping
    public ResponseEntity<List<StudentDto>> getStudents(HttpServletRequest request){
        authorizationService.getStudentFromHttpRequest(request);
        return ResponseEntity.ok(studentService.getStudents());
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentDto> getStudentById(@PathVariable Long id, HttpServletRequest request){
        authorizationService.getStudentFromHttpRequest(request);
        return ResponseEntity.ok(studentService.getStudentById(id));
    }

    @DeleteMapping("/{id}")
    public void deleteStudentById(@PathVariable Long id, HttpServletRequest request){
        authorizationService.getStudentFromHttpRequest(request);
        studentService.deleteStudentById(id);
    }

    @PutMapping("/{studentId}/course/{courseId}")
    public ResponseEntity<String> enrollToCourse(@PathVariable Long studentId, @PathVariable Long courseId, HttpServletRequest request) throws Exception {
        authorizationService.getStudentFromHttpRequest(request);
        courseService.enrollToCourse(studentId, courseId);
        return ResponseEntity.ok("Enrollment is successful!");

    }
    @GetMapping("/courses")
    public ResponseEntity<List<StudentCourseDto>> getCourses(HttpServletRequest request) {
        Student student = authorizationService.getStudentFromHttpRequest(request);
        return ResponseEntity.ok(studentService.getCourses(student));
    }
    @DeleteMapping("/{studentId}/course/{courseId}")
    public ResponseEntity<String> unEnrollFromCourse(@PathVariable Long studentId, @PathVariable Long courseId, HttpServletRequest request) throws Exception {
        authorizationService.getStudentFromHttpRequest(request);
        courseService.unEnrollFromCourse(studentId, courseId);
        return ResponseEntity.ok("Unenrollment is successful!");
    }




}
