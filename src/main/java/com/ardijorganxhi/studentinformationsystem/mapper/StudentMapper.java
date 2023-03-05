package com.ardijorganxhi.studentinformationsystem.mapper;


import com.ardijorganxhi.studentinformationsystem.config.security.PasswordEncoder;
import com.ardijorganxhi.studentinformationsystem.dto.RegistrationDto;
import com.ardijorganxhi.studentinformationsystem.model.Student;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class StudentMapper {

    private final PasswordEncoder passwordEncoder;

    public Student registerDto(RegistrationDto registrationDto){
        Student student = new Student();
        student.setName(registrationDto.getName());
        student.setSurname(registrationDto.getSurname());
        student.setEmail(registrationDto.getEmail());
        student.setPassword(passwordEncoder.bCryptPasswordEncoder().encode(registrationDto.getPassword()));

        return student;
    }


}
