package com.ardijorganxhi.studentinformationsystem.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "courses")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Course{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String courseCode;
    private String courseName;
    private int midterm;
    private int finalExam;

    @OneToMany
    @JoinColumn(name = "student_id")
    private List<User> users;

    @ManyToOne
    @JoinColumn(name = "teacher_id")
    private User user;



}
