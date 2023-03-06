package com.ardijorganxhi.studentinformationsystem.model;


import com.ardijorganxhi.studentinformationsystem.embedded.StudentCourseId;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "students_courses")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class StudentCourse {

    @EmbeddedId
    private StudentCourseId studentCourseId = new StudentCourseId();

    @ManyToOne
    @MapsId("studentId")
    @JoinColumn(name = "student_id")
    @JsonIgnore
    private Student student;

    @ManyToOne
    @MapsId("courseId")
    @JoinColumn(name = "course_id")
    @JsonIgnore
    private Course course;

    private int midtermGrade = 0;
    private int finalGrade = 0;
}
