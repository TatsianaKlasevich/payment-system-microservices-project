package com.klasevich.itrex.lab.repository;

import com.klasevich.itrex.lab.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRoleRepository extends JpaRepository<UserRole, Integer> {
}
