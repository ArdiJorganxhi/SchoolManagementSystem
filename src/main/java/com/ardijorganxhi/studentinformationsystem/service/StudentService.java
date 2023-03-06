package com.ardijorganxhi.studentinformationsystem.service;


import com.ardijorganxhi.studentinformationsystem.dto.StudentDto;
import com.ardijorganxhi.studentinformationsystem.mapper.StudentMapper;
import com.ardijorganxhi.studentinformationsystem.model.Student;
import com.ardijorganxhi.studentinformationsystem.repository.StudentRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class StudentService implements UserDetailsService {

    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;

    public Student findByEmail(String email){
        return studentRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException(""));
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return findByEmail(username);
    }

    public List<StudentDto> getStudents(){
        return studentMapper.listToDto(studentRepository.findAll());
    }

    public StudentDto getStudentById(Long id){
        return studentMapper.toDto(studentRepository.findById(id).get());
    }

    public Student getStudent(Long id){
        return studentRepository.findById(id).get();
    }

    public void deleteStudentById(Long id){
          studentRepository.deleteById(id);
    }
}
