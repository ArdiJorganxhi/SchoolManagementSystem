package com.ardijorganxhi.studentinformationsystem.embedded;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@Embeddable
@Getter
@Setter
public class StudentCourseId implements Serializable {

    private long studentId;
    private long courseId;
}
