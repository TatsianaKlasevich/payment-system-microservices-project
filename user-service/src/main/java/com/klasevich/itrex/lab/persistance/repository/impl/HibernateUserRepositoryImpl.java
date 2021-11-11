package com.klasevich.itrex.lab.persistance.repository.impl;

import com.klasevich.itrex.lab.persistance.entity.User;
import com.klasevich.itrex.lab.persistance.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@RequiredArgsConstructor
@Repository
public class HibernateUserRepositoryImpl implements UserRepository {
    private static final String FIND_ALL_USERS_QUERY = "from User";
    private static final String DELETE_QUERY = "delete from User where id=: id";
    private final SessionFactory sessionFactory;

    @Override
    public List<User> findAll() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery(FIND_ALL_USERS_QUERY, User.class).list();
    }

    @Transactional
    @Override
    public void save(User user) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(user);
    }

    @Transactional
    @Override
    public void saveAll(List<User> users) {
        Session session = sessionFactory.getCurrentSession();
        for (User user : users) {
            session.save(user);
        }
    }

    @Transactional
    @Override
    public void deleteById(Long id) {
        Session session = sessionFactory.getCurrentSession();
        Query<User> query = session.createQuery(DELETE_QUERY);
        query.setParameter("id", id);
        query.executeUpdate();
    }

    @Override
    public User findById(Long id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(User.class, id);
    }
}
