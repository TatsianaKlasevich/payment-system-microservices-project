package com.klasevich.itrex.lab.persistance.repository;

import com.klasevich.itrex.lab.persistance.entity.User;

import java.util.List;

public interface UserRepository {
    List<User> findAll();

    void save(User user);

    void saveAll(List<User> users);

    void deleteById(Long id);

    void update(User user);

    User findById(Long id);
}
