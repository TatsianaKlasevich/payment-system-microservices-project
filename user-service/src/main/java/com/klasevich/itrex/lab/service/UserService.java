package com.klasevich.itrex.lab.service;

import com.klasevich.itrex.lab.persistance.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {

    User getUserById(Long userId);

    Long createUser(User user);

    User deleteUser(Long userId);

    User updateUser(User user);

    Page<User> findAllUsers(Pageable pageable);
}
