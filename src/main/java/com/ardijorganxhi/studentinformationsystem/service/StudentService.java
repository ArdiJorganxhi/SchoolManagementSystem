package com.ardijorganxhi.studentinformationsystem.service;


import com.ardijorganxhi.studentinformationsystem.dto.UserDto;
import com.ardijorganxhi.studentinformationsystem.model.Student;
import com.ardijorganxhi.studentinformationsystem.model.Teacher;
import com.ardijorganxhi.studentinformationsystem.model.User;
import com.ardijorganxhi.studentinformationsystem.repository.StudentRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class StudentService implements UserDetailsService {

    private final StudentRepository studentRepository;

    public Student findByEmail(String email){
        return studentRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException(""));
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return findByEmail(username);
    }
}
