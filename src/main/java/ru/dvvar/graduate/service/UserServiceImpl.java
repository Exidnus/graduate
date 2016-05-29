package ru.dvvar.graduate.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.dvvar.graduate.model.User;
import ru.dvvar.graduate.repository.UserRepository;
import ru.dvvar.graduate.web.LoggedUser;

import java.util.List;

/**
 * Created by Dmitriy_Varygin on 05.04.2016.
 */
@Service("userService")
public class UserServiceImpl implements UserService, UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User get(int id) {
        return userRepository.get(id);
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public void update(User user) {
        userRepository.save(user);
    }

    @Override
    public boolean delete(int id) {
        return userRepository.delete(id);
    }

    @Override
    public List<User> getAll() {
        return userRepository.getAll();
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        final User user = userRepository.getByEmail(email.toLowerCase());
        if (user == null) throw new UsernameNotFoundException("User with email " + email + " wasn't found");
        return new LoggedUser(user);
    }
}
