package com.klasevich.itrex.lab.service.impl;

import com.klasevich.itrex.lab.controller.dto.UserRequestDTO;
import com.klasevich.itrex.lab.exception.UserNotFoundException;
import com.klasevich.itrex.lab.persistance.entity.User;
import com.klasevich.itrex.lab.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@ActiveProfiles("test")
class UserServiceImplTest {

    @Autowired
    private UserService userService;

    @Test
    void getUserByIdEmail_ShouldBeTheSame() {
        // given
        UserRequestDTO user = UserRequestDTO.builder()
                .email("sergi@gmail.com")
                .name("Tanya")
                .secondName("Konstantinovich")
                .surname("Petrov")
                .dateOfBirth(LocalDate.of(1989, 9, 11))
                .identityPassportNumber("123214NK784545L")
                .phoneNumber("+375443650684")
                .build();
        Long id = userService.createUser(user);
        String expected = "sergi@gmail.com";

        // when
        User resultUser = userService.getUserById(id);
        String actual = resultUser.getEmail();

        // then
        assertEquals(actual, expected);
    }

    @Test
    void userNotFoundExceptionTest() {
        //given
        String message = "User hasn't been found";

        // when
        UserNotFoundException exception = assertThrows(UserNotFoundException.class,
                () -> {
                    throw new UserNotFoundException(message);
                });

        // then
        assertEquals(message, exception.getMessage());
    }
}