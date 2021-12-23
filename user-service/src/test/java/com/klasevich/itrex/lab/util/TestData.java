package com.klasevich.itrex.lab.util;

import com.klasevich.itrex.lab.controller.dto.UserRequestDTO;
import com.klasevich.itrex.lab.persistance.entity.User;

import java.time.LocalDate;

public class TestData {
    private TestData() {
    }

    public static User createNewUser() {
        return User.builder()
                .email("sergis@gmail.com")
                .name("Serg")
                .secondName("Konstantinovich")
                .surname("Petrov")
                .dateOfBirth(LocalDate.of(1989, 9, 11))
                .identityPassportNumber("123214NK78454L")
                .phoneNumber("+375443650684")
                .build();
    }

    public static User createSecondUser() {
        return User.builder()
                .email("ser@gmail.com")
                .name("Serg")
                .secondName("Konstantinovich")
                .surname("Petrov")
                .dateOfBirth(LocalDate.of(1989, 9, 11))
                .identityPassportNumber("12K914NK78454L")
                .phoneNumber("+375443650684")
                .build();
    }

    public static UserRequestDTO createNewUserRequestDTO() {
        return UserRequestDTO.builder()
                .email("sergis@gmail.com")
                .name("Serg")
                .secondName("Konstantinovich")
                .surname("Petrov")
                .dateOfBirth(LocalDate.of(1989, 9, 11))
                .identityPassportNumber("123214NK78454L")
                .phoneNumber("+375443650684")
                .build();
    }
}
