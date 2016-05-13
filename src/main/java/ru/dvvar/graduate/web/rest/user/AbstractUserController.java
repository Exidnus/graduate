package ru.dvvar.graduate.web.rest.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.dvvar.graduate.model.User;
import ru.dvvar.graduate.service.UserService;

import java.util.List;

/**
 * Created by Dmitriy_Varygin on 13.05.2016.
 */
public abstract class AbstractUserController {

    private final Logger log = LoggerFactory.getLogger(AbstractUserController.class);

    private UserService service;

    public List<User> getAll() {
        log.info("getAll");
        return service.getAll();
    }

    public User get(int id) {
        log.info("get " + id);
        return service.get(id);
    }

    public User create(User user) {
        user.setId(null);
        log.info("create " + user);
        return service.save(user);
    }

    public void delete(int id) {
        log.info("delete " + id);
        service.delete(id);
    }

    public void update(User user, int id) {
        user.setId(id);
        log.info("update " + user);
        service.update(user);
    }

    public void update(User user) {
        log.info("update " + user);
        service.update(user);
    }

    public User getByMail(String email) {
        log.info("getByEmail " + email);
        return service.getByEmail(email);
    }
}
