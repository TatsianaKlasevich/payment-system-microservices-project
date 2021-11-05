package com.klasevich.itrex.lab.persistance.repository.impl;

import com.klasevich.itrex.lab.persistance.entity.UserRole;
import com.klasevich.itrex.lab.persistance.repository.UserRoleRepository;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class HibernateUserRoleRepositoryImpl implements UserRoleRepository {
    private final Session session;

    public HibernateUserRoleRepositoryImpl(Session session) {
        this.session = session;
    }

    @Override
    public List<UserRole> selectAll() {

        return session.createQuery("from UserRole", UserRole.class).list();
    }

    @Override
    public void update(UserRole userRole) {
        session.update(userRole);
    }

    @Override
    public UserRole findById(Long id) {
        return session.get(UserRole.class, id);
    }
}
