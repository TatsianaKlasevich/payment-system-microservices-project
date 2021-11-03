package com.klasevich.itrex.lab.repository.impl;

import com.klasevich.itrex.lab.entity.User;
import com.klasevich.itrex.lab.exception.RepositoryException;
import com.klasevich.itrex.lab.repository.UserRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class HibernateUserRepositoryImpl implements UserRepository {
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
    public void addAll(List<User> users) throws RepositoryException {
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            for (User user : users) {
                session.save(user);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RepositoryException("Transaction unable", e);
        }
    }

    @Override
    public int delete(int id) throws RepositoryException {
        Transaction transaction = null;
        int result = id;
        try {
            transaction = session.beginTransaction();
            User user = session.find(User.class, id);
            if (user != null) {
                session.delete(user);
            } else {
                result = 0;
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RepositoryException("Transaction unable", e);
        }
        return result;
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
