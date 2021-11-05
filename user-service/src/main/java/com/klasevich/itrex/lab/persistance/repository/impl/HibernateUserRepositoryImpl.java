package com.klasevich.itrex.lab.persistance.repository.impl;

import com.klasevich.itrex.lab.persistance.entity.User;
import com.klasevich.itrex.lab.persistance.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class HibernateUserRepositoryImpl implements UserRepository {
    private final Session session;

    @Override
    public List<User> findAll() {
        return session.createQuery("from User", User.class).list();
    }

    @Override
    public void save(User user) {
        session.save(user);
    }

    @Override
    public void saveAll(List<User> users) {
        for (User user : users) {
            session.save(user);
        }
    }

    @Override
    public void deleteById(Long id) {
        session.createQuery("delete from User where id=: id");
    }

    @Override
    public void update(User user) {
        session.update(user);
    }

    @Override
    public User findById(Long id) {
        return session.get(User.class, id);
    }
}
