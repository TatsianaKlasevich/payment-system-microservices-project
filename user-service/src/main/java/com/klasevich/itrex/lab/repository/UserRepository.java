package com.klasevich.itrex.lab.repository;

import com.klasevich.itrex.lab.entity.User;
import com.klasevich.itrex.lab.exception.RepositoryException;

import java.util.List;

public interface UserRepository {
    List<User> selectAll();

    void add(User user);

    void addAll(List<User> users) throws RepositoryException;

    int delete(int id) throws RepositoryException;

    void update(User user);

    User findById(int id);
}
