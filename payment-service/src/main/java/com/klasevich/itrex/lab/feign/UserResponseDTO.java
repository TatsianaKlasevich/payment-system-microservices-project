package com.klasevich.itrex.lab.feign;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class UserResponseDTO {
    private Long userId;
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

