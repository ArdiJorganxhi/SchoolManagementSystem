package com.ardijorganxhi.studentinformationsystem.model;


import com.ardijorganxhi.studentinformationsystem.embedded.StudentCourseId;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "grades")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Grade {

    @EmbeddedId
    private StudentCourseId studentCourseId;

    @ManyToOne
    @MapsId("studentId")
    @JoinColumn(name = "student_id")
    @JsonIgnore
    private User user;

    @ManyToOne
    @MapsId("courseId")
    @JoinColumn(name = "course_id")
    @JsonIgnore
    private Course course;

    private int midtermGrade;
    private int finalGrade;
}
