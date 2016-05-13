package ru.dvvar.graduate.web.rest.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.dvvar.graduate.model.User;
import ru.dvvar.graduate.service.UserService;

import java.util.List;

/**
 * Created by Dmitriy_Varygin on 05.04.2016.
 */
public class UserController {

    private static final Logger LOG = LoggerFactory.getLogger(UserController.class);

    UserService service;

    public User create(User user) {
        user.setId(null);
        LOG.info("create " + user);
        return service.save(user);
    }

    public User get(int id) {
        LOG.info("get " + id);
        return service.get(id);
    }

    public void delete(int id) {
        LOG.info("delete " + id);
        service.delete(id);
    }

    public void update(User user) {
        LOG.info("update " + user);
        service.update(user);
    }

    public List<User> getAll() {
        LOG.info("getAll ");
        return service.getAll();
    }
}
