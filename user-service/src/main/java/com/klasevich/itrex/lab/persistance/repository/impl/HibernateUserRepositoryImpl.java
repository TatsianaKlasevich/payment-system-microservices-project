package com.klasevich.itrex.lab.persistance.repository.impl;

import com.klasevich.itrex.lab.persistance.entity.User;
import com.klasevich.itrex.lab.persistance.repository.UserRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class HibernateUserRepositoryImpl implements UserRepository {
    private static final String FIND_ALL_USERS_QUERY = "from User";
    private static final String DELETE_QUERY = "delete from User where id=: id";
    private SessionFactory sessionFactory;

    @Autowired
    public HibernateUserRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<User> findAll() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery(FIND_ALL_USERS_QUERY, User.class).list();
    }

    @Override
    public void save(User user) {
        Session session = sessionFactory.getCurrentSession();
        session.save(user);
    }

    @Override
    public void saveAll(List<User> users) {
        Session session = sessionFactory.getCurrentSession();
        for (User user : users) {
            session.save(user);
        }
    }

    @Override
    public void deleteById(Long id) {
        Session session = sessionFactory.getCurrentSession();
        Query<User> query = session.createQuery(DELETE_QUERY);
        query.setParameter("id", id);
        query.executeUpdate();
    }

    @Override
    public void update(User user) {
        Session session = sessionFactory.getCurrentSession();
        session.update(user);
    }

    @Override
    public User findById(Long id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(User.class, id);
    }
}
