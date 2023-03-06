package com.ardijorganxhi.studentinformationsystem.service;

import com.ardijorganxhi.studentinformationsystem.dto.CourseDto;
import com.ardijorganxhi.studentinformationsystem.dto.CreateCourseDto;
import com.ardijorganxhi.studentinformationsystem.mapper.CourseMapper;
import com.ardijorganxhi.studentinformationsystem.model.Course;
import com.ardijorganxhi.studentinformationsystem.model.Student;
import com.ardijorganxhi.studentinformationsystem.model.StudentCourse;
import com.ardijorganxhi.studentinformationsystem.model.Teacher;
import com.ardijorganxhi.studentinformationsystem.repository.CourseRepository;
import com.ardijorganxhi.studentinformationsystem.repository.StudentCourseRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CourseService {

    private final CourseRepository courseRepository;
    private final CourseMapper courseMapper;
    private final StudentService studentService;
    private final StudentCourseRepository studentCourseRepository;


    public Course createCourse(CreateCourseDto courseDto, Teacher teacher){
        Course course = courseMapper.createCourse(courseDto, teacher);
        courseRepository.save(course);
        return course;
    }

    public List<CourseDto> getCourses(){
        return courseMapper.listToDto(courseRepository.findAll());
    }
    public List<CourseDto> getCoursesByTeacher(Long id){

        return courseMapper.listToDto(courseRepository.findAllByTeacherId(id));
    }

    public void enrollToCourse(Long studentId, Long courseId) throws Exception{

        Course course = courseRepository.findById(courseId).orElseThrow(() -> new Exception("Course not found!"));
        Student student = studentService.getStudent(studentId);

        StudentCourse studentCourse = studentCourseRepository.findByStudentIdAndCourseId(studentId, courseId);
        student.getCourses().add(studentCourse);
        studentCourseRepository.save(studentCourse);

    }








}
