package com.klasevich.itrex.lab.repository;

import com.klasevich.itrex.lab.config.ApplicationContextConfiguration;
import com.klasevich.itrex.lab.entity.User;
import com.klasevich.itrex.lab.exception.RepositoryException;
import com.klasevich.itrex.lab.service.FlywayService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
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
        List<User> result = userRepository.selectAll();
        int actual = result.size();

        //then
        assertEquals(actual, expected);
    }

    @Test
    void findByIdUserShouldBeTheSame() {
        //given
        User expected = new User(2, "segrei@gmail.com", "e12345", "Tanya", "Konstantinovich",
                "Petrov", LocalDate.of(1989, 9, 11),
                "1214NK784545L", "+375443650684");

        int id = 2;

        // when
        User actual = userRepository.findById(id);

        //then
        assertEquals(actual, expected);
    }

    @Test
    void updateUserAndCheckChangesShouldBeMade() {
        //given
        User user = new User(2, "segrei@gmail.com", "e12345", "Tanya", "Konstantinovich",
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
    void deleteUserAndCheckNumberOfUserShouldBeTheSame() throws RepositoryException {
        //given
        int id = 1;
        int expected = userRepository.selectAll().size() - 1;

        // when
        userRepository.delete(id);
        List<User> users = userRepository.selectAll();
        int actual = users.size();

        //then
        assertEquals(actual, expected);
    }

    @Test
    void addAllUsersNumberShouldBeRight() throws SQLException, RepositoryException {
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
        userRepository.addAll(users);
        List<User> newUsers = userRepository.selectAll();
        int actual = newUsers.size();

        //then
        assertEquals(actual, expected);
    }
}
