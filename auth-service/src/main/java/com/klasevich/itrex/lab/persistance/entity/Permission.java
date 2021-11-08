package com.klasevich.itrex.lab.persistance.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "permission")
@Data
public class Permission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;
}
