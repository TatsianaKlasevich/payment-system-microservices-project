package com.klasevich.itrex.lab;

import com.klasevich.itrex.lab.config.ApplicationContextConfiguration;
import com.klasevich.itrex.lab.persistance.entity.User;
import com.klasevich.itrex.lab.persistance.entity.UserRole;
import com.klasevich.itrex.lab.persistance.repository.UserRepository;
import com.klasevich.itrex.lab.persistance.repository.UserRoleRepository;
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

        User userFoundById = userRepository.findById(2L);
        logger.info("show user by id=2 - {}", userFoundById);

        User addedUser = User.builder()
                .email("gleb@gmail.com")
                .password("12345s")
                .name("Gleb")
                .secondName("Olegovich")
                .surname("Pivovarov")
                .dateOfBirth(LocalDate.of(1976, 03, 14))
                .identityPassportNumber("121433NK324545L")
                .phoneNumber("+375337088994")
                .build();
        userRepository.save(addedUser);
        logger.info("add user - {}", addedUser);

        users = userRepository.findAll();
        logger.info("Show all users after adding - {}", users);

        List<User> newUsers = new ArrayList<>();
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
        newUsers.add(user2);

        userRepository.saveAll(newUsers);
        logger.info("add some users {}", newUsers);
        users = userRepository.findAll();
        logger.info("Show all users after adding some users {}", users);

        // user for updating
        User userFoundByIdToUpdate = userRepository.findById(3l);
        logger.info("show user by id=3 to update - {}", userFoundByIdToUpdate);
        addedUser.setPhoneNumber("+375443088994");
        userRepository.save(addedUser);
        User userFoundByIdAfterUpdating = userRepository.findById(3l);
        logger.info("show user by id=3 after updating - {}", userFoundByIdAfterUpdating);

        userRepository.deleteById(5l);
        logger.info("User with id=5 have been deleted");
        users = userRepository.findAll();
        logger.info("Show all users after deleting - {}", users);
    }
}


