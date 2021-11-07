package com.klasevich.itrex.lab.persistance.repository.impl;

import com.klasevich.itrex.lab.persistance.entity.UserRole;
import com.klasevich.itrex.lab.persistance.repository.UserRoleRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Repository
public class HibernateUserRoleRepositoryImpl implements UserRoleRepository {
    private SessionFactory sessionFactory;

    @Autowired
    public HibernateUserRoleRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<UserRole> selectAll() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from UserRole", UserRole.class).list();
    }

    @Override
    public void update(UserRole userRole) {
        Session session = sessionFactory.getCurrentSession();
        session.update(userRole);
    }

    @Override
    public UserRole findById(Long id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(UserRole.class, id);
    }
}
