package com.klasevich.itrex.lab.repository.impl;

import com.klasevich.itrex.lab.entity.User;
import com.klasevich.itrex.lab.repository.BaseRepositoryTest;
import com.klasevich.itrex.lab.repository.UserRepository;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static com.klasevich.itrex.lab.util.HibernateUtil.getSessionFactory;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class HibernateUserRepositoryImplTest extends BaseRepositoryTest {

    private final UserRepository userRepository;


    public HibernateUserRepositoryImplTest() {
        super();
        userRepository = new HibernateUserRepositoryImpl(getSessionFactory().openSession());

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
        User actual = (User) userRepository.findById(id);

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
    void deleteUserAndCheckNumberOfUserShouldBeTheSame() {
        //given
        int id = 1;
        int expected = 1;

        // when
        userRepository.delete(id);
        List<User> users = userRepository.selectAll();
        int actual = users.size();

        //then
        assertEquals(actual, expected);
    }

    @Test
    void addAllUsersNumberShouldBeRight() throws SQLException {
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
