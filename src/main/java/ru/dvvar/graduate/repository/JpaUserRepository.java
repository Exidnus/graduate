package ru.dvvar.graduate.repository;

import ru.dvvar.graduate.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by Dmitriy_Varygin on 15.05.2016.
 */
public class JpaUserRepository implements UserRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public User save(User user) {
        return null;
    }

    @Override
    public User get(int id) {
        return em.find(User.class, id);
    }

    @Override
    public void delete(int id) {

    }

    @Override
    public List<User> getAll() {
        return null;
    }
}
