package com.klasevich.itrex.lab.controller.dto;

import lombok.*;

import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class UserRequestDTO {
    private String email;
    private String password;
    private String name;
    private String secondName;
    private String surname;
    private LocalDate dateOfBirth;
    private String identityPassportNumber;
    private String phoneNumber;
    private List<Long> roles;
    private List<Long> cards;
}
