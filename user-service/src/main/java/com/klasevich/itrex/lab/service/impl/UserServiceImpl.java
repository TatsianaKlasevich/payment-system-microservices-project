package com.klasevich.itrex.lab.service.impl;

import com.klasevich.itrex.lab.controller.dto.UserRequestDTO;
import com.klasevich.itrex.lab.mappers.UserRequestDTOToUserMapper;
import com.klasevich.itrex.lab.persistance.entity.User;
import com.klasevich.itrex.lab.exception.UserNotFoundException;
import com.klasevich.itrex.lab.persistance.repository.UserRepository;
import com.klasevich.itrex.lab.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserRequestDTOToUserMapper userRequestDTOToUserMapper;


    public User getUserById(Long userId) {
        return userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("Unable to find user " +
                "with id: " + userId));
    }

    public Long createUser(UserRequestDTO userRequestDTO) {
        User user = userRequestDTOToUserMapper.convert(userRequestDTO);
        return userRepository.save(user).getUserId();
    }

    public User updateUser(Long userId, UserRequestDTO userRequestDTO) {
        User user = userRequestDTOToUserMapper.convert(userRequestDTO);
        user.setUserId(userId);
        return userRepository.save(user);
    }

    public User deleteUser(Long userId) {
        User deletedUser = getUserById(userId);
        userRepository.deleteById(userId);
        return deletedUser;
    }
}