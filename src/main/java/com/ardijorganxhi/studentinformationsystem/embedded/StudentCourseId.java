package com.ardijorganxhi.studentinformationsystem.embedded;

import lombok.Data;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@Embeddable
public class StudentCourseId implements Serializable {

    private long studentId;
    private long courseId;
}
