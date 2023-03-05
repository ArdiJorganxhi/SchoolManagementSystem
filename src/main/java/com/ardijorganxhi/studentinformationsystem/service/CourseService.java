package com.ardijorganxhi.studentinformationsystem.service;

import com.ardijorganxhi.studentinformationsystem.dto.CreateCourseDto;
import com.ardijorganxhi.studentinformationsystem.mapper.CourseMapper;
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
    private final CourseMapper courseMapper;


    public Course createCourse(CreateCourseDto courseDto, Teacher teacher){
        Course course = courseMapper.createCourse(courseDto, teacher);
        courseRepository.save(course);
        return course;
    }

    public List<Course> getCourses(){
        return courseRepository.findAll();
    }
    public List<Course> getCoursesByTeacher(Long id){
        return courseRepository.findAllByTeacherId(id);
    }





}
