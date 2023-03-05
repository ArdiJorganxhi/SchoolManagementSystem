package com.ardijorganxhi.studentinformationsystem.mapper;

import com.ardijorganxhi.studentinformationsystem.config.security.PasswordEncoder;
import com.ardijorganxhi.studentinformationsystem.dto.RegistrationDto;
import com.ardijorganxhi.studentinformationsystem.model.Teacher;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class TeacherMapper {

    private final PasswordEncoder passwordEncoder;

    public Teacher registrationDto(RegistrationDto registrationDto){
        Teacher teacher = new Teacher();
        teacher.setName(registrationDto.getName());
        teacher.setSurname(registrationDto.getSurname());
        teacher.setEmail(registrationDto.getEmail());
        teacher.setPassword(passwordEncoder.bCryptPasswordEncoder().encode(registrationDto.getPassword()));

        return teacher;
    }
}
