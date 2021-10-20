package com.klasevich.itrex.lab.repository.impl;

import com.klasevich.itrex.lab.entity.User;
import com.klasevich.itrex.lab.repository.UserRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;

import java.sql.SQLException;
import java.util.List;

public class HibernateUserRepositoryImpl implements UserRepository {
    private static final Logger logger = LogManager.getLogger();
    private final Session session;

    public HibernateUserRepositoryImpl(Session session) {
        this.session = session;
    }

    @Override
    public List<User> selectAll() {
        return session.createQuery("from User", User.class).list();
    }

    @Override
    public void add(User user) {
        session.save(user);
    }

    @Override
    public void addAll(List<User> users) throws SQLException {
        for (User user : users) {
            session.save(user);
        }
    }

    @Override
    public void delete(int id) {
        User user = session.get(User.class, id);
        logger.debug("user {} will be deleted with id {}", user, id);
        session.delete(user);
    }

    @Override
    public void update(User user) {
        session.update(user);
    }

    @Override
    public User findById(int id) {
        return session.get(User.class, id);
    }
}
