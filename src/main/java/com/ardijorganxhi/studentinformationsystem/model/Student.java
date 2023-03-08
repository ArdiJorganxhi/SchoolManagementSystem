package com.ardijorganxhi.studentinformationsystem.model;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "students")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Student implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String surname;
    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;


    @OneToMany(mappedBy = "student")
    private List<StudentCourse> courses;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("STUDENT"));
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
