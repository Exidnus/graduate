package ru.dvvar.graduate.service;

import ru.dvvar.graduate.model.User;

import java.util.List;

/**
 * Created by Dmitriy_Varygin on 05.04.2016.
 */
public interface UserService {

    User save(User user);

    User get(int id);

    void delete(int id);

    void update(User user);

    List<User> getAll();

}
