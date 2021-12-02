package com.klasevich.itrex.lab.controller.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class UserResponseDTO {

    private Long userId;

    private String email;

    private String name;

    private String secondName;

    private String surname;

    private LocalDate dateOfBirth;

    private String identityPassportNumber;

    private String phoneNumber;
}

