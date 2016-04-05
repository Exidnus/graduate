package ru.dvvar.graduate.service;

import ru.dvvar.graduate.model.User;
import ru.dvvar.graduate.repository.UserRepository;

import java.util.List;

/**
 * Created by Dmitriy_Varygin on 05.04.2016.
 */
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public User get(int id) {
        return userRepository.get(id);
    }

    @Override
    public void delete(int id) {
        userRepository.delete(id);
    }

    @Override
    public void update(User user) {
        userRepository.save(user);
    }

    @Override
    public List<User> getAll() {
        return userRepository.getAll();
    }

    @Override
    public User getByEmail(String email) {
        return userRepository.getByEmail(email);
    }
}
