package com.klasevich.itrex.lab.persistance.repository;

import com.klasevich.itrex.lab.persistance.entity.UserRole;

import java.util.List;

public interface UserRoleRepository {
    List<UserRole> selectAll();

    void update(UserRole userRole);

    UserRole findById(Long id);
}
