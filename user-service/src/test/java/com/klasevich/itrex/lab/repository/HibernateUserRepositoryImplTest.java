package com.klasevich.itrex.lab.repository;

import com.klasevich.itrex.lab.config.ApplicationContextConfiguration;
import com.klasevich.itrex.lab.persistance.entity.User;
import com.klasevich.itrex.lab.persistance.repository.UserRepository;
import com.klasevich.itrex.lab.service.FlywayService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class HibernateUserRepositoryImplTest {
    private final FlywayService flywayService;
    private final ApplicationContext applicationContext;
    private final UserRepository userRepository;


    public HibernateUserRepositoryImplTest() {
        applicationContext = new AnnotationConfigApplicationContext(ApplicationContextConfiguration.class);
        flywayService = applicationContext.getBean(FlywayService.class);
        userRepository = applicationContext.getBean(UserRepository.class);
    }

    @AfterEach
    public void cleanDB() {
        flywayService.clean();
    }

    @Test
    void findAllValidDataShouldReturnTheNumberOfUsers() {
        //given
        int expected = 2;

        // when
        List<User> result = userRepository.findAll();
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
        User actual = userRepository.findById(id);

        //then
        assertEquals(actual, expected);
    }

    @Test
    void updateUserAndCheckChangesShouldBeMade() {
        //given
        User user = new User(2l, "segrei@gmail.com", "e12345", "Tanya", "Konstantinovich",
                "Petrov", LocalDate.of(1989, 9, 11),
                "1214NK784545L", "+375443650684");
        user.setSurname("Gribalev");
        String expected = "Gribalev";

        // when
        userRepository.update(user);
        String actual = user.getSurname();

        //then
        assertEquals(actual, expected);
    }

    @Test
    void deleteUserAndCheckNumberOfUserShouldBeTheSame() {
        //given
        Long id = 1l;
        Long expected = userRepository.findAll().size() - 1l;

        // when
        userRepository.deleteById(id);
        List<User> users = userRepository.findAll();
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
        userRepository.saveAll(users);
        List<User> newUsers = userRepository.findAll();
        int actual = newUsers.size();

        //then
        assertEquals(actual, expected);
    }
}
