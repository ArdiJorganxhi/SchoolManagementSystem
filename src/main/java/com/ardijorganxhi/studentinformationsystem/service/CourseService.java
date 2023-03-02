package com.ardijorganxhi.studentinformationsystem.service;

import com.ardijorganxhi.studentinformationsystem.dto.CourseCreationDto;
import com.ardijorganxhi.studentinformationsystem.model.Course;
import com.ardijorganxhi.studentinformationsystem.repository.CourseRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CourseService {

    private final CourseRepository courseRepository;


    public Course createCourse(CourseCreationDto creationDto){

        Course course = new Course();

        course.setCourseName(creationDto.getCourseName());
        course.setCourseCode(creationDto.getCourseCode());
        course.setMidterm(creationDto.getMidterm());
        course.setFinalExam(creationDto.getFinalExam());

        courseRepository.save(course);

        return course;

    }

    public void deleteCourse(Long id){

        courseRepository.deleteById(id);
    }
}
