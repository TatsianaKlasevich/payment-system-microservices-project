package com.klasevich.itrex.lab;

import com.klasevich.itrex.lab.entity.User;
import com.klasevich.itrex.lab.entity.UserRole;
import com.klasevich.itrex.lab.repository.UserRepository;
import com.klasevich.itrex.lab.repository.UserRoleRepository;
import com.klasevich.itrex.lab.repository.impl.HibernateUserRepositoryImpl;
import com.klasevich.itrex.lab.repository.impl.HibernateUserRoleRepositoryImpl;
import com.klasevich.itrex.lab.service.FlywayService;

import com.klasevich.itrex.lab.util.HibernateUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Runner {
    private static final Logger logger = LogManager.getLogger();

    public static void main(String[] args) throws SQLException {
        logger.info("===================START APP======================");
        logger.info("================START MIGRATION===================");
        FlywayService flywayService = new FlywayService();
        flywayService.migrate();

        logger.info("================CREATE SESSION===================");
        Session session = HibernateUtil.getSessionFactory().openSession();
        UserRepository userRepository = new HibernateUserRepositoryImpl(session);
        UserRoleRepository userRoleRepository = new HibernateUserRoleRepositoryImpl(session);

        List<User> users = userRepository.selectAll();
        logger.info("show all users - {}", users);
        List<UserRole> userRoles = userRoleRepository.selectAll();
        logger.info("show all user roles - {}", userRoles);

        User userFoundById = (User) userRepository.findById(2);
        logger.info("show user by id=1 - {}", userFoundById);
        UserRole userRoleFoundById = (UserRole) userRoleRepository.findById(1);
        logger.info("show user role by id=1 - {}", userRoleFoundById);

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

        users = userRepository.selectAll();
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

        Session session2 = HibernateUtil.getSessionFactory().openSession();
        UserRepository userRepository2 = new HibernateUserRepositoryImpl(session2);
        Transaction transaction = null;
        try {
            transaction = session2.beginTransaction();

            userRepository2.addAll(newUsers);
            logger.info("add some users {}", newUsers);

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            logger.error(e);
        }

        users = userRepository.selectAll();
        logger.info("Show all users after adding some users {}", users);

        // user for updating
        User userFoundByIdToUpdate = (User) userRepository.findById(3);
        logger.info("show user by id=3 to update - {}", userFoundByIdToUpdate);

        addedUser.setPhoneNumber("+375443088994");
        userRepository.update(addedUser);

        User userFoundByIdAfterUpdating = userRepository.findById(3);
        logger.info("show user by id=3 after updating - {}", userFoundByIdAfterUpdating);

        Session session3 = HibernateUtil.getSessionFactory().openSession();
        UserRepository userRepository3 = new HibernateUserRepositoryImpl(session2);
        Transaction transaction2 = null;
        try {
            transaction2 = session2.beginTransaction();

            userRepository3.delete(5);
            System.out.println("User with id=5 have been deleted");

            transaction2.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            logger.error(e);
        }

        users = userRepository.selectAll();
        logger.info("Show all users after deleting - {}", users);


        logger.info("===============CLOSE SESSION===============");
        session.close();
        session2.close();
        session3.close();
        logger.info("===============SHUT DOWN APP===============");
    }
}


