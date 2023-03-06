package com.ardijorganxhi.studentinformationsystem.mapper;

import com.ardijorganxhi.studentinformationsystem.config.security.PasswordEncoder;
import com.ardijorganxhi.studentinformationsystem.dto.RegistrationDto;
import com.ardijorganxhi.studentinformationsystem.dto.TeacherDto;
import com.ardijorganxhi.studentinformationsystem.model.Teacher;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class TeacherMapper {

    private final PasswordEncoder passwordEncoder;

    public Teacher registrationDto(RegistrationDto registrationDto){

        return Teacher.builder()
                .name(registrationDto.getName())
                .surname(registrationDto.getSurname())
                .email(registrationDto.getEmail())
                .password(passwordEncoder.bCryptPasswordEncoder().encode(registrationDto.getPassword()))
                .build();
    }

    public TeacherDto toDto(Teacher teacher){
        return TeacherDto.builder()
                .id(teacher.getId())
                .name(teacher.getName())
                .surname(teacher.getSurname())
                .teacherCourses(teacher.getCourses())
                .build();
    }

    public List<TeacherDto> listToDto(List<Teacher> teachers){
        return teachers.stream().map(this::toDto).collect(Collectors.toList());
    }
}
