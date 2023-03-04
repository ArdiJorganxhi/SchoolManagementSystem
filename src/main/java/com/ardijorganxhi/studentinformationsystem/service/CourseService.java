package com.ardijorganxhi.studentinformationsystem.service;

import com.ardijorganxhi.studentinformationsystem.dto.CreateCourseDto;
import com.ardijorganxhi.studentinformationsystem.model.Course;
import com.ardijorganxhi.studentinformationsystem.model.Teacher;
import com.ardijorganxhi.studentinformationsystem.repository.CourseRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CourseService {

    private final CourseRepository courseRepository;


    public Course createCourse(CreateCourseDto courseDto, Teacher teacher){
        Course course = new Course();
        course.setCourseCode(courseDto.getCourseCode());
        course.setCourseName(courseDto.getCourseName());
        course.setMidterm(courseDto.getMidterm());
        course.setFinalExam(courseDto.getFinalExam());
        course.setTeacher(teacher);
        return course;
    }

    public List<Course> getCourses(){
        return courseRepository.findAll();
    }
    public List<Course> getCoursesByTeacher(Long id){
        return courseRepository.findAllByTeacherId(id);
    }





}
