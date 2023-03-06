package com.ardijorganxhi.studentinformationsystem.mapper;

import com.ardijorganxhi.studentinformationsystem.config.security.PasswordEncoder;
import com.ardijorganxhi.studentinformationsystem.dto.CourseDto;
import com.ardijorganxhi.studentinformationsystem.dto.CreateCourseDto;
import com.ardijorganxhi.studentinformationsystem.model.Course;
import com.ardijorganxhi.studentinformationsystem.model.Teacher;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class CourseMapper {

    public Course createCourse(CreateCourseDto courseDto, Teacher teacher){

        return Course.builder()
                .courseCode(courseDto.getCourseCode())
                .courseName(courseDto.getCourseName())
                .midterm(courseDto.getMidterm())
                .finalExam(courseDto.getFinalExam())
                .teacher(teacher)
                .build();
    }

    public CourseDto toDto(Course course){
        return CourseDto.builder()
                .courseCode(course.getCourseCode())
                .courseName(course.getCourseName())
                .midterm(course.getMidterm())
                .finalExam(course.getFinalExam())
                .build();
    }
    public List<CourseDto> listToDto(List<Course> courses){
        return courses.stream().map(this::toDto).collect(Collectors.toList());
    }


}
