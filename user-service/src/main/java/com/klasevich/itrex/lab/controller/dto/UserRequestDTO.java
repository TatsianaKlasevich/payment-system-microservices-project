package com.klasevich.itrex.lab.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserRequestDTO {
    @NotEmpty(message = "Email should not be empty")
    @Email(message = "Email should be valid")
    private String email;

    @NotEmpty(message = "Name should not be empty")
    @Size(min = 2, max = 30, message = "Name should be between 2 and 30 characters")
    private String name;

    @NotEmpty(message = "Second name should not be empty")
    @Size(min = 2, max = 30, message = "Name should be between 2 and 30 characters")
    private String secondName;

    @NotEmpty(message = "Surname should not be empty")
    @Size(min = 2, max = 30, message = "Name should be between 2 and 30 characters")
    private String surname;

    @NotEmpty(message = "Date of birth should not be empty")
    @Past(message = "Date of birth should be before current")
    private LocalDate dateOfBirth;

    @NotEmpty(message = "Passport number should not be empty")
    @Size(min = 14, max = 14, message = "Passport number should have 14 characters")
    private String identityPassportNumber;

    @NotEmpty(message = "Phone number should not be empty")
    @Size(min = 13, max = 13, message = "Phone number should have 13 characters")
    @Pattern(regexp = "\\+375(44|33|25|29)\\d{7}", message = "Phone number should be valid")
    private String phoneNumber;
    private List<Long> cards;
}
