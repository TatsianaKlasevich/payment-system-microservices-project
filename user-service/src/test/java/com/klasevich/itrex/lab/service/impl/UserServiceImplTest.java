package com.klasevich.itrex.lab.service.impl;

import com.klasevich.itrex.lab.BaseRepositoryTest;
import com.klasevich.itrex.lab.config.ApplicationContextConfiguration;
import com.klasevich.itrex.lab.persistance.dto.UserResponseDTO;
import com.klasevich.itrex.lab.persistance.entity.User;
import com.klasevich.itrex.lab.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UserServiceImplTest extends BaseRepositoryTest {
    private static final Logger logger = LogManager.getLogger();

    private final ApplicationContext applicationContext;
    private final UserService userService;


    public UserServiceImplTest() {
        applicationContext = new AnnotationConfigApplicationContext(ApplicationContextConfiguration.class);
        userService = applicationContext.getBean(UserService.class);
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
        User expected = new User(2l, "segrei@gmail.com", "e12345", "Tanya", "Konstantinovich",
                "Petrov", LocalDate.of(1989, 9, 11),
                "1214NK784545L", "+375443650684");

        Long id = 2l;

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
        User user1 = new User();
        user1.setEmail("andrey@gmail.com");
        user1.setPassword("12345s");
        user1.setName("Andrey");
        user1.setSecondName("Semenovich");
        user1.setSurname("Kryuk");
        user1.setDateOfBirth(LocalDate.of(1966, 03, 20));
        user1.setIdentityPassportNumber("121233NK324545L");
        user1.setPhoneNumber("+375447088994");
        users.add(user1);

        User user2 = new User();
        user2.setEmail("kirill@gmail.com");
        user2.setPassword("12345s");
        user2.setName("Kirill");
        user2.setSecondName("Vasiljevich");
        user2.setSurname("Bondarev");
        user2.setDateOfBirth(LocalDate.of(1979, 03, 20));
        user2.setIdentityPassportNumber("121433NK32424545L");
        user2.setPhoneNumber("+375447088994");
        users.add(user2);

        int expected = 4;

        // when
        userService.saveAll(users);
        List<UserResponseDTO> newUsers = userService.findAllUsers();
        int actual = newUsers.size();

        //then
        assertEquals(actual, expected);
    }
}