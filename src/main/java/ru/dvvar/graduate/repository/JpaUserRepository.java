package ru.dvvar.graduate.repository;

import ru.dvvar.graduate.model.User;

import java.util.List;

/**
 * Created by Dmitriy_Varygin on 15.05.2016.
 */
public class JpaUserRepository implements UserRepository {

    @Override
    public User save(User user) {
        return null;
    }

    @Override
    public User get(int id) {
        return null;
    }

    @Override
    public void delete(int id) {

    }

    @Override
    public List<User> getAll() {
        return null;
    }
}
