package com.klasevich.itrex.lab.repository;

import com.klasevich.itrex.lab.entity.User;

import java.sql.SQLException;
import java.util.List;

public interface UserRepository {
    List<User> selectAll();

    void add(User user);

    void addAll(List<User> users) throws SQLException;

    void delete(int id);

    void update(User user);

    User findById(int id);
}
