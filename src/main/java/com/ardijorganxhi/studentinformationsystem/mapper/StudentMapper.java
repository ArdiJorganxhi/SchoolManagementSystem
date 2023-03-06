package com.ardijorganxhi.studentinformationsystem.mapper;


import com.ardijorganxhi.studentinformationsystem.config.security.PasswordEncoder;
import com.ardijorganxhi.studentinformationsystem.dto.RegistrationDto;
import com.ardijorganxhi.studentinformationsystem.dto.StudentDto;
import com.ardijorganxhi.studentinformationsystem.model.Student;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class StudentMapper {

    private final PasswordEncoder passwordEncoder;

    public Student registerDto(RegistrationDto registrationDto){


        return Student.builder()
                .name(registrationDto.getName())
                .surname(registrationDto.getSurname())
                .email(registrationDto.getEmail())
                .password(passwordEncoder.bCryptPasswordEncoder().encode(registrationDto.getPassword()))
                .build();
    }

    public StudentDto toDto(Student student){
        return StudentDto.builder()
                .id(student.getId())
                .name(student.getName())
                .surname(student.getSurname())
                .studentCourses(student.getCourses())
                .build();
    }

    public List<StudentDto> listToDto(List<Student> students){
        return students.stream().map(this::toDto).collect(Collectors.toList());
    }




}
