package com.klasevich.itrex.lab.repository;

import com.klasevich.itrex.lab.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

}
