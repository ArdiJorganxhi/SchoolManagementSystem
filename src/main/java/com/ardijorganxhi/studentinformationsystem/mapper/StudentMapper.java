package com.ardijorganxhi.studentinformationsystem.mapper;


import com.ardijorganxhi.studentinformationsystem.config.security.PasswordEncoder;
import com.ardijorganxhi.studentinformationsystem.dto.RegistrationDto;
import com.ardijorganxhi.studentinformationsystem.dto.UserDto;
import com.ardijorganxhi.studentinformationsystem.model.Student;
import com.ardijorganxhi.studentinformationsystem.model.Teacher;
import com.ardijorganxhi.studentinformationsystem.model.User;
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

    public UserDto toDto(Student student){
        return UserDto.builder()
                .id(student.getId())
                .name(student.getName())
                .surname(student.getSurname())
                .courses(student.getCourses())
                .build();
    }

    public List<UserDto> listToDto(List<Student> students){
        return students.stream().map(this::toDto).collect(Collectors.toList());
    }




}
