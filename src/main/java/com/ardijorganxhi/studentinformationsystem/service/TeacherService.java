package com.ardijorganxhi.studentinformationsystem.service;

import com.ardijorganxhi.studentinformationsystem.dto.UserDto;
import com.ardijorganxhi.studentinformationsystem.mapper.TeacherMapper;
import com.ardijorganxhi.studentinformationsystem.model.Teacher;
import com.ardijorganxhi.studentinformationsystem.model.User;
import com.ardijorganxhi.studentinformationsystem.repository.TeacherRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TeacherService implements UserDetailsService {

    private final TeacherRepository teacherRepository;
    private final TeacherMapper teacherMapper;

    public Teacher findByEmail(String email){
        return teacherRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException(""));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        return findByEmail(username);
    }

    public List<UserDto> getTeachers(){
        return teacherMapper.listToDto(teacherRepository.findAll());
    }

    public UserDto getTeacherById(Long id){
        return teacherMapper.toDto(teacherRepository.findById(id).get());
    }

    public UserDto getTeacher(Long id){
        return teacherMapper.toDto(teacherRepository.findById(id).get());
    }

    public void deleteTeacherById(Long id){
        teacherRepository.deleteById(id);
    }
}
