package com.klasevich.itrex.lab.service.impl;

import com.klasevich.itrex.lab.exception.UserNotFoundException;
import com.klasevich.itrex.lab.persistance.entity.User;
import com.klasevich.itrex.lab.persistance.repository.UserRepository;
import com.klasevich.itrex.lab.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    private static final String USER_EXCEPTION_MESSAGE = "Unable to find user with id: %d";
    private final UserRepository userRepository;

    @Override
    public User getUserById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(String.format(USER_EXCEPTION_MESSAGE, userId)));
    }

    @Override
    @Transactional
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Override
    @Transactional
    public User updateUser(User user) {
        return userRepository.save(user);
    }

    @Override
    @Transactional
    public User deleteUser(Long userId) {
        User deletedUser = getUserById(userId);
        userRepository.deleteById(userId);
        return deletedUser;
    }

    @Override
    public Page<User> findAllUsers(Pageable pageable) {
        return userRepository.findAll(pageable);
    }
}