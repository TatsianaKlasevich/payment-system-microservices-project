package com.klasevich.itrex.lab.persistance.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "users")
public class User extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long userId;

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
    private List<Long> roles;

    @ElementCollection
    private List<Long> cards;


}
