package com.klasevich.itrex.lab.service.impl;

import com.klasevich.itrex.lab.persistance.entity.User;
import com.klasevich.itrex.lab.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ActiveProfiles("test")
class UserServiceImplTest {

    @Autowired
    private UserService userService;

    @Test
    void getUserByIdShouldBeTheSame() {
        User expected = User.builder()
                .userId(2L)
                .email("segrei@gmail.com")
                .password("e12345")
                .name("Tanya")
                .secondName("Konstantinovich")
                .surname("Petrov")
                .dateOfBirth(LocalDate.of(1989, 9, 11))
                .identityPassportNumber("1214NK784545L")
                .phoneNumber("+375443650684")
                .build();
        Long id = 2L;

        // when
        User actual = userService.getUserById(id);

        //then
        assertEquals(actual, expected);
    }
}