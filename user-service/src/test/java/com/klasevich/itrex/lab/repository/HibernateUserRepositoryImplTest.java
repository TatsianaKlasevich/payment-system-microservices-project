package com.klasevich.itrex.lab.repository;

import com.klasevich.itrex.lab.BaseRepositoryTest;
import com.klasevich.itrex.lab.config.ApplicationContextConfiguration;
import com.klasevich.itrex.lab.persistance.entity.User;
import com.klasevich.itrex.lab.persistance.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class HibernateUserRepositoryImplTest extends BaseRepositoryTest {

    private final ApplicationContext applicationContext;
    private final UserRepository userRepository;


    public HibernateUserRepositoryImplTest() {
        applicationContext = new AnnotationConfigApplicationContext(ApplicationContextConfiguration.class);
        userRepository = applicationContext.getBean(UserRepository.class);
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

        Long id = 2l;

        // when
        User actual = userRepository.findById(id);

        //then
        assertEquals(actual, expected);
    }

    @Test
    void updateUserAndCheckChangesShouldBeMade() {
        //given
        User user = User.builder()
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
        user.setSurname("Gribalev");
        String expected = "Gribalev";

        // when
        userRepository.save(user);
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
        int expected = 4;

        // when
        userRepository.saveAll(users);
        List<User> newUsers = userRepository.findAll();
        int actual = newUsers.size();

        //then
        assertEquals(actual, expected);
    }
}
