package com.klasevich.itrex.lab.persistance.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
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
