package com.ardijorganxhi.studentinformationsystem.service;

import com.ardijorganxhi.studentinformationsystem.model.User;
import com.ardijorganxhi.studentinformationsystem.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {


    private final UserRepository userRepository;
    public User findByEmail(String email){

        return userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException(""));

    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return findByEmail(username);
    }

    public List<User> getStudents(){
        return userRepository.findAll();
    }


    public User getStudentById(Long id) throws Exception{
        return userRepository.findById(id).orElseThrow(() -> new Exception("Student not found!"));
    }
}
