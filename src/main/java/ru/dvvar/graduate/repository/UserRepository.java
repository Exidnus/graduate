package ru.dvvar.graduate.repository;

import ru.dvvar.graduate.model.User;

import java.util.List;

/**
 * Created by Dmitriy_Varygin on 05.04.2016.
 */
public interface UserRepository {

    User save(User user);

    User get(int id);

    void delete(int id);

    List<User> getAll();

}
