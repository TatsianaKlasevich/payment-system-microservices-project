package com.klasevich.itrex.lab.persistance.repository;

import com.klasevich.itrex.lab.persistance.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserDetailRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String name);
}
