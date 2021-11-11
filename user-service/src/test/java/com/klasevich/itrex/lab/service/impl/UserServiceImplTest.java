package com.klasevich.itrex.lab.service.impl;

import com.klasevich.itrex.lab.persistance.dto.UserRequestDTO;
import com.klasevich.itrex.lab.persistance.dto.UserResponseDTO;
import com.klasevich.itrex.lab.persistance.entity.User;
import com.klasevich.itrex.lab.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = TestConfiguration.class)
class UserServiceImplTest {

    private final UserService userService;

    UserServiceImplTest(UserService userService) {
        this.userService = userService;
    }

    @Test
    void findAllValidDataShouldReturnTheNumberOfUsers() {
        //given
        int expected = 2;

        // when
        List<UserResponseDTO> result = userService.findAllUsers();
        int actual = result.size();

        //then
        assertEquals(actual, expected);
    }

    @Test
    void findByIdUserShouldBeTheSame() {
        //given
        User expected = User.builder()
                .id(2L)
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

    @Test
    void deleteUserAndCheckNumberOfUserShouldBeTheSame() {
        //given
        Long id = 1l;
        Long expected = userService.findAllUsers().size() - 1l;

        // when
        userService.deleteUser(id);
        List<UserResponseDTO> users = userService.findAllUsers();
        int actual = users.size();

        //then
        assertEquals(actual, expected);
    }

    @Test
    void addAllUsersNumberShouldBeRight() {
        //given
        List<User> users = new ArrayList<>();
        User user1 = User.builder()
                .email("andrey@gmail.com")
                .password("12345s")
                .name("Andrey")
                .secondName("Semenovich")
                .surname("Kryuk")
                .dateOfBirth(LocalDate.of(1966, 03, 20))
                .identityPassportNumber("121233NK324545L")
                .phoneNumber("+375447088994")
                .build();
        users.add(user1);

        User user2 = User.builder()
                .email("kirill@gmail.com")
                .password("12345s")
                .name("Kirill")
                .secondName("Vasiljevich")
                .surname("Bondarev")
                .dateOfBirth(LocalDate.of(1979, 03, 20))
                .identityPassportNumber("121433NK32424545L")
                .phoneNumber("+375447088994")
                .build();
        users.add(user2);

        int expected = 4;

        // when
        userService.saveAll(users);
        List<UserResponseDTO> newUsers = userService.findAllUsers();
        int actual = newUsers.size();

        //then
        assertEquals(actual, expected);
    }

    @Test
    void updateUserAndCheckChangesShouldBeMade() {
        //given
        UserRequestDTO user = UserRequestDTO.builder()
                .email("andrey@gmail.com")
                .password("12345s")
                .name("Andrey")
                .secondName("Semenovich")
                .surname("Kryuk")
                .dateOfBirth(LocalDate.of(1966, 03, 20))
                .identityPassportNumber("121233NK324545L")
                .phoneNumber("+375447088994")
                .build();
        Long userId = userService.createUser(user);
        user.setSurname("Gribalev");
        String expected = "Gribalev";

        // when
        userService.updateUser(userId, user);
        String actual = user.getSurname();

        //then
        assertEquals(actual, expected);
    }
}