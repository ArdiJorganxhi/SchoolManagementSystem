package com.ardijorganxhi.studentinformationsystem.mapper;

import com.ardijorganxhi.studentinformationsystem.dto.StudentCourseDto;
import com.ardijorganxhi.studentinformationsystem.model.StudentCourse;
import com.ardijorganxhi.studentinformationsystem.service.CourseService;
import lombok.AllArgsConstructor;
import lombok.Builder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@Builder
public class StudentCourseMapper {

    private final CourseMapper courseMapper;

    public StudentCourseDto toDto(StudentCourse studentCourse){

        return StudentCourseDto.builder()
                .course(courseMapper.toDto(studentCourse.getCourse()))
                .midterm(studentCourse.getMidtermGrade())
                .finalExam(studentCourse.getFinalGrade())
                .build();
    }

    public List<StudentCourseDto> listToDto(List<StudentCourse> studentCourseList){
        return studentCourseList.stream().map(this::toDto).collect(Collectors.toList());
    }
}
