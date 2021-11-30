package com.klasevich.itrex.lab.feign;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
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

