package ru.dvvar.graduate.web.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import ru.dvvar.graduate.model.User;
import ru.dvvar.graduate.service.UserService;

import java.util.List;

/**
 * Created by Dmitriy_Varygin on 13.05.2016.
 */
public abstract class AbstractUserController {

    private static final Logger LOG = LoggerFactory.getLogger(AbstractUserController.class);

    @Autowired
    private UserService service;

    public List<User> getAll() {
        LOG.info("getAll");
        return service.getAll();
    }

    public User get(int id) {
        LOG.info("get " + id);
        return service.get(id);
    }

    public User create(User user) {
        user.setId(null);
        LOG.info("create " + user);
        return service.save(user);
    }

    public void delete(int id) {
        LOG.info("delete " + id);
        service.delete(id);
    }

    public void update(User user, int id) {
        user.setId(id);
        LOG.info("update " + user);
        service.update(user);
    }
}
