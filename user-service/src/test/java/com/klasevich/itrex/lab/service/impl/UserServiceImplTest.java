package com.klasevich.itrex.lab.service.impl;

import com.klasevich.itrex.lab.exception.UserNotFoundException;
import com.klasevich.itrex.lab.persistance.entity.User;
import com.klasevich.itrex.lab.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@ActiveProfiles("test")
class UserServiceImplTest {

    @Autowired
    private UserService userService;

    @Test
    void createUser_getUserById_emailShouldBeTheSame() { //todo
        // given
        User user = createNewUser();
        Long id = userService.createUser(user);
        String expected = user.getEmail();

        // when
        User resultUser = userService.getUserById(id);
        String actual = resultUser.getEmail();

        // then
        assertEquals(actual, expected);
        userService.deleteUser(id);
    }

    @Test
    void userNotFoundException_whenUserGetByIdNotExist() {
        //given
        Long id = userService.createUser(createNewUser());
        userService.deleteUser(id);
        String message = "Unable to find user with id: " + id;

        // when
        UserNotFoundException exception = assertThrows(UserNotFoundException.class,
                () -> {
                    userService.getUserById(id);
                });

        // then
        assertThat(message).isEqualTo(exception.getMessage());
    }

    @Test
    void findSomePageOfUsers_shouldReturnValidNumberOfUsers() {
        //given
        Long id1 = userService.createUser(createNewUser());
        Long id2 = userService.createUser(createSecondUser());
        Pageable pageable = PageRequest.of(1, 2);
        int expected = 2;

        // when
        List<User> result = userService.findAllUsers(pageable).getContent();
        int actual = result.size();

        //then
        assertEquals(actual, expected);
        userService.deleteUser(id1);
        userService.deleteUser(id2);
    }

    @Test
    void updateUserAndCheck_changesShouldBeMade() {
        //given
        User user = createNewUser();
        Long id = userService.createUser(user);
        user.setSurname("Gribalev");
        String expected = "Gribalev";

        // when
        userService.updateUser(user);
        String actual = user.getSurname();

        //then
        assertThat(expected).isEqualTo(actual);
        userService.deleteUser(id);
    }

    private User createNewUser() {
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

    private User createSecondUser() {
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
}