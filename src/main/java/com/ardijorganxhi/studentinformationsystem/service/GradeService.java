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
        int midtermPercentage = gradeDto.getMidterm() / 100;
        int finalExamPercentage = gradeDto.getFinalExam() / 100;
        StudentCourse studentCourse = studentCourseRepository.findByStudentIdAndCourseId(studentId, courseId);
        studentCourse.setMidtermGrade(gradeDto.getMidterm());
        studentCourse.setFinalGrade(gradeDto.getFinalExam());
        studentCourse.setGrade(gradeDto.getMidterm() * midtermPercentage + gradeDto.getFinalExam() * finalExamPercentage);
        studentCourseRepository.save(studentCourse);

        return studentCourse;
    }


}
