package com.klasevich.itrex.lab.service;

import com.klasevich.itrex.lab.persistance.dto.UserRequestDTO;
import com.klasevich.itrex.lab.persistance.dto.UserResponseDTO;
import com.klasevich.itrex.lab.persistance.entity.User;

import java.util.List;

public interface UserService {

    User getUserById(Long userId);

    Long createUser(UserRequestDTO user);

    void deleteUser(Long userId);

    void updateUser(Long userId, UserRequestDTO userRequestDTO);

    List<UserResponseDTO> findAllUsers();

    void saveAll(List<User> users);
}
