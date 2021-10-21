package com.klasevich.itrex.lab.repository;

import com.klasevich.itrex.lab.entity.Card;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardRepository extends JpaRepository<Card,Integer> {
}
