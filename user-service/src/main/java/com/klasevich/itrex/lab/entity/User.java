package com.klasevich.itrex.lab.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int userId;

    private String email;

    private String password;

    private String name;

    @Column(name = "second_name")
    private String secondName;

    private String surname;

    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    @Column(name = "identity_passport_number")
    private String identityPassportNumber;

    @Column(name = "phone_number")
    private String phoneNumber;

    @ElementCollection
    private List<Integer> roles;

    @ElementCollection
    private List<Integer> cards;

    public User(String email, String password, String name, String secondName, String surname, LocalDate dateOfBirth,
                String identityPassportNumber, String phoneNumber, List<Integer> roles, List<Integer> cards) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.secondName = secondName;
        this.surname = surname;
        this.dateOfBirth = dateOfBirth;
        this.identityPassportNumber = identityPassportNumber;
        this.phoneNumber = phoneNumber;
        this.roles = roles;
        this.cards = cards;
    }
}
