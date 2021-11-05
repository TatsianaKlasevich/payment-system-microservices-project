package com.klasevich.itrex.lab;

import com.klasevich.itrex.lab.config.ApplicationContextConfiguration;
import com.klasevich.itrex.lab.persistance.entity.User;
import com.klasevich.itrex.lab.persistance.entity.UserRole;
import com.klasevich.itrex.lab.persistance.repository.UserRepository;
import com.klasevich.itrex.lab.persistance.repository.UserRoleRepository;
import com.klasevich.itrex.lab.service.FlywayService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Runner {
    private static final Logger logger = LogManager.getLogger();

    public static void main(String[] args) {
        logger.info("===================START APP======================");
        logger.info("================START MIGRATION===================");

        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(ApplicationContextConfiguration.class);
        applicationContext.getBean(FlywayService.class);
        UserRepository userRepository = applicationContext.getBean(UserRepository.class);
        UserRoleRepository userRoleRepository = applicationContext.getBean(UserRoleRepository.class);

        workWithHibernate(userRepository, userRoleRepository);

        logger.info("===============SHUT DOWN APP===============");
    }

    public static void workWithHibernate(UserRepository userRepository, UserRoleRepository userRoleRepository) {

        List<User> users = userRepository.findAll();
        logger.info("show all users - {}", users);
        List<UserRole> userRoles = userRoleRepository.selectAll();
        logger.info("show all user roles - {}", userRoles);

        User userFoundById = userRepository.findById(2l);
        logger.info("show user by id=2 - {}", userFoundById);
        UserRole userRoleFoundById = userRoleRepository.findById(1l);
        logger.info("show user role by id=1 - {}", userRoleFoundById);

        User addedUser = new User();
        addedUser.setEmail("gleb@gmail.com");
        addedUser.setPassword("12345s");
        addedUser.setName("Gleb");
        addedUser.setSecondName("Olegovich");
        addedUser.setSurname("Pivovarov");
        addedUser.setDateOfBirth(LocalDate.of(1976, 03, 14));
        addedUser.setIdentityPassportNumber("121433NK324545L");
        addedUser.setPhoneNumber("+375337088994");
        userRepository.save(addedUser);
        logger.info("add user - {}", addedUser);

        users = userRepository.findAll();
        logger.info("Show all users after adding - {}", users);

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

        userRepository.saveAll(newUsers);
        logger.info("add some users {}", newUsers);
        users = userRepository.findAll();
        logger.info("Show all users after adding some users {}", users);

        // user for updating
        User userFoundByIdToUpdate = userRepository.findById(3l);
        logger.info("show user by id=3 to update - {}", userFoundByIdToUpdate);
        addedUser.setPhoneNumber("+375443088994");
        userRepository.update(addedUser);
        User userFoundByIdAfterUpdating = userRepository.findById(3l);
        logger.info("show user by id=3 after updating - {}", userFoundByIdAfterUpdating);

        userRepository.deleteById(5l);
        logger.info("User with id=5 have been deleted");
        users = userRepository.findAll();
        logger.info("Show all users after deleting - {}", users);
    }
}


