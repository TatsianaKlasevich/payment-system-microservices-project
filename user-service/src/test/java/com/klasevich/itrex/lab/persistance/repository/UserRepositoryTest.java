package com.klasevich.itrex.lab.persistance.repository;

import com.klasevich.itrex.lab.persistance.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import static com.klasevich.itrex.lab.util.TestData.createNewUser;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
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
}