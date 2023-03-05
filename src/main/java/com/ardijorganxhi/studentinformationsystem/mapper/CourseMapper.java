package com.ardijorganxhi.studentinformationsystem.mapper;

import com.ardijorganxhi.studentinformationsystem.config.security.PasswordEncoder;
import com.ardijorganxhi.studentinformationsystem.dto.CreateCourseDto;
import com.ardijorganxhi.studentinformationsystem.model.Course;
import com.ardijorganxhi.studentinformationsystem.model.Teacher;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class CourseMapper {

    public Course createCourse(CreateCourseDto courseDto, Teacher teacher){

        Course course = new Course();

        course.setCourseCode(courseDto.getCourseCode());
        course.setCourseName(courseDto.getCourseName());
        course.setMidterm(courseDto.getMidterm());
        course.setFinalExam(courseDto.getFinalExam());
        course.setTeacher(teacher);

        return course;
    }
}
