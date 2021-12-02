package com.klasevich.itrex.lab.persistance.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;

@EqualsAndHashCode(callSuper = false)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "users")
public class User extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long userId;

    @Column(name = "email")
    private String email;

    @Column(name = "name")
    private String name;

    @Column(name = "second_name")
    private String secondName;

    @Column(name = "surname")
    private String surname;

    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    @Column(name = "identity_passport_number")
    private String identityPassportNumber;

    @Column(name = "phone_number")
    private String phoneNumber;
}
