package com.ardijorganxhi.studentinformationsystem.service;

import com.ardijorganxhi.studentinformationsystem.dto.GradeDto;
import com.ardijorganxhi.studentinformationsystem.model.Course;
import com.ardijorganxhi.studentinformationsystem.model.Student;
import com.ardijorganxhi.studentinformationsystem.model.StudentCourse;
import com.ardijorganxhi.studentinformationsystem.repository.CourseRepository;
import com.ardijorganxhi.studentinformationsystem.repository.StudentCourseRepository;
import com.ardijorganxhi.studentinformationsystem.repository.StudentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class GradeService {

    private final StudentCourseRepository studentCourseRepository;
    private final CourseRepository courseRepository;
    private final StudentService studentService;

    public StudentCourse gradeStudent(Long studentId, Long courseId, GradeDto gradeDto) throws Exception{
        Course course = courseRepository.findById(courseId).orElseThrow(() -> new Exception("Course not found!"));
        Student student = studentService.getStudent(studentId);
        int midtermPercentage = course.getMidterm() / 100;
        int finalExamPercentage = course.getFinalExam() / 100;
        StudentCourse studentCourse = new StudentCourse();
        studentCourse.setStudent(student);
        studentCourse.setCourse(course);
        studentCourse.setMidtermGrade(gradeDto.getMidterm());
        studentCourse.setFinalGrade(gradeDto.getFinalExam());

        return studentCourse;
    }


}
