package ru.dvvar.graduate.service;

import ru.dvvar.graduate.model.User;

import java.util.List;

/**
 * Created by Dmitriy_Varygin on 05.04.2016.
 */
public interface UserService {

    User get(int id);

    User save(User user);

    void update(User user);

    boolean delete(int id);

    List<User> getAll();

    //User getByEmail(String email);

}
