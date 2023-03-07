package com.ardijorganxhi.studentinformationsystem.service;


import com.ardijorganxhi.studentinformationsystem.config.security.JwtTokenUtility;
import com.ardijorganxhi.studentinformationsystem.dto.LoginDto;
import com.ardijorganxhi.studentinformationsystem.dto.RegistrationDto;
import com.ardijorganxhi.studentinformationsystem.mapper.StudentMapper;
import com.ardijorganxhi.studentinformationsystem.mapper.TeacherMapper;
import com.ardijorganxhi.studentinformationsystem.model.Student;
import com.ardijorganxhi.studentinformationsystem.model.Teacher;
import com.ardijorganxhi.studentinformationsystem.repository.StudentRepository;
import com.ardijorganxhi.studentinformationsystem.repository.TeacherRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthService {

    private final StudentRepository studentRepository;
    private final TeacherRepository teacherRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtility jwtTokenUtility;
    private final TeacherService teacherService;
    private final StudentService studentService;
    private final StudentMapper studentMapper;
    private final TeacherMapper teacherMapper;



    public Student registerStudent(RegistrationDto registrationDto){
        Student student = studentMapper.registerDto(registrationDto);
        studentRepository.save(student);
        return student;
    }

    public Teacher registerTeacher(RegistrationDto registrationDto){
        Teacher teacher = teacherMapper.registrationDto(registrationDto);
        teacherRepository.save(teacher);
        return teacher;
    }





    public String loginStudent(LoginDto loginDto) throws Exception{

        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword()));
        } catch (BadCredentialsException e){
            throw new Exception("BadCredentials");
        }
        return jwtTokenUtility.generateTokenForStudent(studentService.findByEmail(loginDto.getEmail()));
    }

    public String loginTeacher(LoginDto loginDto) throws Exception{

        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword()));
        } catch (BadCredentialsException e){
            throw new Exception("BadCredentials");
        }
        return jwtTokenUtility.generateTokenForTeacher(teacherService.findByEmail(loginDto.getEmail()));
    }



}
