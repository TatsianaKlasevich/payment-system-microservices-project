package com.klasevich.itrex.lab.repository;

import com.klasevich.itrex.lab.entity.User;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface UserRepository {
    List<User> findAll();

    void add(User user);

    void addAll(List<User> users) throws SQLException;

    void delete(int id);

    void update(User user);

    Optional<User> findById(int id);
}
