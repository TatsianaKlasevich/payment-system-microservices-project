package com.klasevich.itrex.lab;

import com.klasevich.itrex.lab.entity.User;
import com.klasevich.itrex.lab.repository.UserRepository;
import com.klasevich.itrex.lab.repository.impl.JDBCUserRepositoryImpl;
import com.klasevich.itrex.lab.service.FlywayService;
import org.h2.jdbcx.JdbcConnectionPool;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.klasevich.itrex.lab.properties.Properties.*;

public class Runner {

    public static void main(String[] args) throws SQLException {
        System.out.println("===================START APP======================");
        System.out.println("================START MIGRATION===================");
        FlywayService flywayService = new FlywayService();
        flywayService.migrate();

        System.out.println("===============CREATE CONNECTION POOL===============");
        JdbcConnectionPool jdbcConnectionPool = JdbcConnectionPool.create(H2_URL, H2_USER, H2_PASSWORD);

        System.out.println("===============CREATE UserRepository===============");
        UserRepository userRepository = new JDBCUserRepositoryImpl(jdbcConnectionPool);
        List<User> users = userRepository.findAll();
        System.out.println("show all users " + users);

        User addedUser = new User();
        addedUser.setEmail("gleb@gmail.com");
        addedUser.setPassword("12345s");
        addedUser.setName("Gleb");
        addedUser.setSecondName("Olegovich");
        addedUser.setSurname("Pivovarov");
        addedUser.setDateOfBirth(LocalDate.of(1986, 03, 20));
        addedUser.setIdentityPassportNumber("121433NK324545L");
        addedUser.setPhoneNumber("+375447088994");
        userRepository.add(addedUser);
        System.out.println("add user " + addedUser);

        users = userRepository.findAll();
        System.out.println("Show all users after adding " + users);

        List<User> newUsers = new ArrayList<>();
        User user1 = new User();
        user1.setEmail("andrey@gmail.com");
        user1.setPassword("12345s");
        user1.setName("Andrey");
        user1.setSecondName("Semenovich");
        user1.setSurname("Kryuk");
        user1.setDateOfBirth(LocalDate.of(1966, 03, 20));
        user1.setIdentityPassportNumber("121233NK324545L");
        user1.setPhoneNumber("+375447088994");
        newUsers.add(user1);

        User user2 = new User();
        user2.setEmail("kirill@gmail.com");
        user2.setPassword("12345s");
        user2.setName("Kirill");
        user2.setSecondName("Vasiljevich");
        user2.setSurname("Bondarev");
        user2.setDateOfBirth(LocalDate.of(1979, 03, 20));
        user2.setIdentityPassportNumber("121433NK32424545L");
        user2.setPhoneNumber("+375447088994");
        newUsers.add(user2);

        userRepository.addAll(newUsers);
        System.out.println("add some users " + newUsers);

        users = userRepository.findAll();
        System.out.println("Show all users after adding some users" + users);

        Optional<User> userFoundById = userRepository.findById(3);
        if (userFoundById.isPresent()) {
            User user = userFoundById.get();
            System.out.println("User by id = 3 is " + user);
        } else {
            System.out.println("User by id = 3 haven't been found");
        }

        // user for updating
        addedUser.setPhoneNumber("+375443088994");
        userRepository.update(addedUser);

        Optional<User> userFoundByIdAfterUpdating = userRepository.findById(3);
        if (userFoundByIdAfterUpdating.isPresent()) {
            User user = userFoundByIdAfterUpdating.get();
            System.out.println("User by id = 3 after updating is " + user);
        } else {
            System.out.println("User by id = 3 haven't been found");
        }

        userRepository.delete(5);
        System.out.println("User with id=5 have been deleted");

        users = userRepository.findAll();
        System.out.println("Show all users after deleting - " + users);

        System.out.println("===============CLOSE ALL UNUSED CONNECTIONS===============");
        jdbcConnectionPool.dispose();
        System.out.println("===============SHUT DOWN APP===============");
    }
}
