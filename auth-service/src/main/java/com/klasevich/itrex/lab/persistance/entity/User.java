package com.klasevich.itrex.lab.persistance.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "email")
    private String email;

    @Column(name = "enabled")
    private boolean enabled;

    @Column(name = "accountNonExpired")
    private boolean accountNonExpired;

    @Column(name = "credentialsNonExpired")
    private boolean credentialsNonExpired;

    @Column(name = "accountNonLocked")
    private boolean accountNonLocked;


    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "roles_users",
            joinColumns = {@JoinColumn(name = "user_id",
                    referencedColumnName = "id")},
            inverseJoinColumns = {
                    @JoinColumn(name = "role_id",
                            referencedColumnName = "id")})
    private List<Role> roles;

    public User(String username, String password, String email, boolean enabled,
                boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked, List<Role> roles) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.enabled = enabled;
        this.accountNonExpired = accountNonExpired;
        this.credentialsNonExpired = credentialsNonExpired;
        this.accountNonLocked = accountNonLocked;
        this.roles = roles;
    }

    public User(User user) {
    }
}
