package com.ardijorganxhi.studentinformationsystem.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "courses")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Course{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String courseCode;
    private String courseName;


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
