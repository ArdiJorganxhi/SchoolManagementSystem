package com.ardijorganxhi.studentinformationsystem.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
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


    @OneToMany(mappedBy = "course")
    private List<StudentCourse> students;

    @ManyToOne
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;

    @JsonBackReference
    public Teacher getTeacher(){
        return teacher;
    }



}
