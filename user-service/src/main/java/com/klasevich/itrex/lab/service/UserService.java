package com.klasevich.itrex.lab.service;

import com.klasevich.itrex.lab.controller.dto.UserRequestDTO;
import com.klasevich.itrex.lab.persistance.entity.User;

public interface UserService {

    User getUserById(Long userId);

    Long createUser(UserRequestDTO user);

    User deleteUser(Long userId);

    User updateUser(Long userId, UserRequestDTO userRequestDTO);
}
