package com.klasevich.itrex.lab.controller.dto;

import com.klasevich.itrex.lab.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class UserResponseDTO {
    private int userId;
    private String email;
    private String password;
    private String name;
    private String secondName;
    private String surname;
    private LocalDate dateOfBirth;
    private String identityPassportNumber;
    private String phoneNumber;
    private List<Integer> roles;
    private List<Integer> cards;

    public UserResponseDTO(User user) {
        userId = user.getUserId();
        email = user.getEmail();
        password = user.getPassword();
        name = user.getName();
        secondName = user.getSecondName();
        surname = user.getSurname();
        dateOfBirth = user.getDateOfBirth();
        identityPassportNumber = user.getIdentityPassportNumber();
        phoneNumber = user.getPhoneNumber();
        roles = user.getRoles();
        cards = user.getCards();
    }
}

