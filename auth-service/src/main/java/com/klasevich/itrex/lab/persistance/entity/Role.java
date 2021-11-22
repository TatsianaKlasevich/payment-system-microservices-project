package com.klasevich.itrex.lab.persistance.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "roles")
@Data
public class Role implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "permissions_roles", joinColumns = {
            @JoinColumn(name = "role_id",
                    referencedColumnName = "id")},
            inverseJoinColumns = {
                    @JoinColumn(name = "permission_id",
                            referencedColumnName = "id")})
    private List<Permission> permissions;
}
