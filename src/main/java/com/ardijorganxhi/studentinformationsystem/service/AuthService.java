package com.ardijorganxhi.studentinformationsystem.service;


import com.ardijorganxhi.studentinformationsystem.config.PasswordEncoder;
import com.ardijorganxhi.studentinformationsystem.config.security.JwtTokenUtility;
import com.ardijorganxhi.studentinformationsystem.dto.LoginDto;
import com.ardijorganxhi.studentinformationsystem.dto.RegistrationDto;
import com.ardijorganxhi.studentinformationsystem.model.Student;
import com.ardijorganxhi.studentinformationsystem.model.Teacher;
import com.ardijorganxhi.studentinformationsystem.model.User;
import com.ardijorganxhi.studentinformationsystem.repository.StudentRepository;
import com.ardijorganxhi.studentinformationsystem.repository.TeacherRepository;
import com.ardijorganxhi.studentinformationsystem.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final StudentRepository studentRepository;
    private final TeacherRepository teacherRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtility jwtTokenUtility;
    private final UserService userService;
    private final TeacherService teacherService;
    private final StudentService studentService;
    private final PasswordEncoder passwordEncoder;



    public Student registerStudent(RegistrationDto registrationDto){
        Student student = new Student();

        student.setName(registrationDto.getName());
        student.setSurname(registrationDto.getSurname());
        student.setEmail(registrationDto.getEmail());
        student.setPassword(passwordEncoder.bCryptPasswordEncoder().encode(registrationDto.getPassword()));


        studentRepository.save(student);

        return student;
    }

    public Teacher registerTeacher(RegistrationDto registrationDto){
        Teacher teacher = new Teacher();

        teacher.setName(registrationDto.getName());
        teacher.setSurname(registrationDto.getSurname());
        teacher.setEmail(registrationDto.getEmail());
        teacher.setPassword(passwordEncoder.bCryptPasswordEncoder().encode(registrationDto.getPassword()));


        teacherRepository.save(teacher);

        return teacher;
    }





    public String loginStudent(LoginDto loginDto) throws Exception{

        try{
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword()));
        } catch (BadCredentialsException e){
            throw new Exception("BadCredentials");
        }
        return jwtTokenUtility.generateTokenForStudent(studentService.findByEmail(loginDto.getEmail()));
    }

    public String loginTeacher(LoginDto loginDto) throws Exception{

        try{
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword()));
        } catch (BadCredentialsException e){
            throw new Exception("BadCredentials");
        }
        return jwtTokenUtility.generateTokenForTeacher(teacherService.findByEmail(loginDto.getEmail()));
    }



}
