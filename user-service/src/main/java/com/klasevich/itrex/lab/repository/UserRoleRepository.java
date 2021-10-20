package com.klasevich.itrex.lab.repository;

import com.klasevich.itrex.lab.entity.UserRole;

import java.util.List;

public interface UserRoleRepository {
    List<UserRole> selectAll();

    void update(UserRole userRole);

    UserRole findById(int id);
}
