package com.klasevich.itrex.lab.repository.impl;

import com.klasevich.itrex.lab.entity.UserRole;
import com.klasevich.itrex.lab.repository.UserRoleRepository;
import org.hibernate.Session;

import java.util.List;

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
    public UserRole findById(int id) {
        return session.get(UserRole.class, id);
    }
}
