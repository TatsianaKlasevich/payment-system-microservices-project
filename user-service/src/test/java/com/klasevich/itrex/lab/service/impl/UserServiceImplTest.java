package com.klasevich.itrex.lab.service.impl;

import com.klasevich.itrex.lab.exception.UserNotFoundException;
import com.klasevich.itrex.lab.persistance.entity.User;
import com.klasevich.itrex.lab.persistance.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
class UserServiceImplTest {
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    @Test
    void getUserById_emailShouldBeTheSame() {
        // given
        User user = createNewUser();
        Long userId = 1L;
        user.setUserId(userId);

        // when
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        User resultUser = userService.getUserById(userId);

        // then
        assertThat(resultUser.getEmail()).isEqualTo(user.getEmail());
    }

    @Test
    void userNotFoundException_whenUserGetByIdNotExist() {
        //given
        Long id = 1L;
        String message = "Unable to find user with id: " + id;

        // when
        when(userRepository.findById(ArgumentMatchers.anyLong())).thenReturn(Optional.empty());
        UserNotFoundException exception = assertThrows(UserNotFoundException.class,
                () -> {
                    userService.getUserById(id);
                });

        // then
        assertThat(message).isEqualTo(exception.getMessage());
    }

    @Test
    void findSomePageOfUsers_shouldReturnValidNumberOfUsers() {
        //given
        List<User> users = new ArrayList<>();
        users.add(createNewUser());
        users.add(createSecondUser());
        Page<User> page = new PageImpl<>((users));
        Pageable pageable = PageRequest.of(1, 2);

        // when
        when(userRepository.findAll(pageable)).thenReturn(page);
        List<User> result = userService.findAllUsers(pageable).getContent();

        //then
        assertThat(result.size()).isEqualTo(users.size());
    }

    @Test
    void updateUserAndCheck_changesShouldBeMade() {
        //given
        User user = createNewUser();
        user.setSurname("Gribalev");

        // when
        when(userRepository.save(user)).thenReturn(user);
        User updatedUser = userService.updateUser(user);

        //then
        assertThat(updatedUser.getSurname()).isEqualTo(user.getSurname());
    }

    @Test
    void createUserAndCheck_UserShouldBeAdded() {
        //given
        User user = createNewUser();

        // when
        when(userRepository.save(user)).thenReturn(user);
        User updatedUser = userService.updateUser(user);

        //then
        assertThat(updatedUser.getSurname()).isEqualTo(user.getSurname());
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

    private User createSecondUser() {
        return User.builder()
                .email("ser@gmail.com")
                .name("Serg")
                .secondName("Konstantinovich")
                .surname("Petrov")
                .dateOfBirth(LocalDate.of(1989, 9, 11))
                .identityPassportNumber("12K914NK78454L")
                .phoneNumber("+375443650684")
                .build();
    }
}