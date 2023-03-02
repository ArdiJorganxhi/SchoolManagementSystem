package com.ardijorganxhi.studentinformationsystem.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class RegistrationDto {

    private String name;
    private String surname;
    private String email;
    private String password;
}
