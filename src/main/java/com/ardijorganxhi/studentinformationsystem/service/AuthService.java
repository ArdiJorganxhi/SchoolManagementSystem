package com.ardijorganxhi.studentinformationsystem.service;


import com.ardijorganxhi.studentinformationsystem.config.PasswordEncoder;
import com.ardijorganxhi.studentinformationsystem.config.security.JwtTokenUtility;
import com.ardijorganxhi.studentinformationsystem.dto.LoginDto;
import com.ardijorganxhi.studentinformationsystem.dto.RegistrationDto;
import com.ardijorganxhi.studentinformationsystem.model.User;
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
    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtility jwtTokenUtility;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;



    public User registerStudent(RegistrationDto registrationDto){
        User user = new User();

        user.setName(registrationDto.getName());
        user.setSurname(registrationDto.getSurname());
        user.setEmail(registrationDto.getEmail());
        user.setPassword(passwordEncoder.bCryptPasswordEncoder().encode(registrationDto.getPassword()));
        user.setRole("STUDENT");

        userRepository.save(user);

        return user;
    }

    public User registerTeacher(RegistrationDto registrationDto){
        User user = new User();

        user.setName(registrationDto.getName());
        user.setSurname(registrationDto.getSurname());
        user.setEmail(registrationDto.getEmail());
        user.setPassword(passwordEncoder.bCryptPasswordEncoder().encode(registrationDto.getPassword()));
        user.setRole("TEACHER");

        userRepository.save(user);

        return user;
    }





    public String loginStudent(LoginDto loginDto) throws Exception{

        try{
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword()));
        } catch (BadCredentialsException e){
            throw new Exception("BadCredentials");
        }
        return jwtTokenUtility.generateToken(userService.findByEmail(loginDto.getEmail()));
    }



}
