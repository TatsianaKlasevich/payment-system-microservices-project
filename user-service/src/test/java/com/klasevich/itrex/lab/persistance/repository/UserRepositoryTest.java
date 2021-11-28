package com.klasevich.itrex.lab.persistance.repository;

import com.klasevich.itrex.lab.persistance.entity.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    void createUser_getUserById_emailShouldBeTheSame() {
        // given
        User user = createNewUser();
        User saveUser = userRepository.save(user);
        Long id = saveUser.getUserId();
        String expected = saveUser.getEmail();

        // when
        User resultUser = userRepository.findById(id).get();
        String actual = resultUser.getEmail();

        // then
        assertEquals(actual, expected);
        userRepository.deleteById(id);
    }

    @Test
    void updateUserAndCheck_changesShouldBeMade() {
        //given
        User user = createNewUser();
        User savedUser = userRepository.save(user);
        user.setSurname("Gribalev");
        String expected = "Gribalev";

        // when
        userRepository.save(user);
        String actual = user.getSurname();

        //then
        assertThat(expected).isEqualTo(actual);
        userRepository.delete(savedUser);
    }

    @Test
    void deleteUser_changesShouldBeMade() {
        //given
        User user = createNewUser();
        User savedUser = userRepository.save(user);
        Long id = savedUser.getUserId();

        //when
        userRepository.delete(savedUser);

        //then
        assertTrue(userRepository.findById(id).isEmpty());
    }

    @Test
    void findAll_sizeShouldBeValid() {
        //given
        User user = createNewUser();
        int expectedSize = userRepository.findAll().size() + 1;

        //when
        userRepository.save(user);
        int actualSize = userRepository.findAll().size();

        //then
        assertEquals(expectedSize, actualSize);
        userRepository.delete(user);
    }

    private User createNewUser() {
        return User.builder()
                .email("sergis@gmail.com")
                .name("Serg")
                .secondName("Konstantinovich")
                .surname("Petrov")
                .dateOfBirth(LocalDate.of(1989, 9, 11))
                .identityPassportNumber("123214NK78454L")
                .phoneNumber("+375443650684")
                .build();
    }
}