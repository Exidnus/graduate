package ru.dvvar.graduate.web.rest.user;

import ru.dvvar.graduate.model.User;
import ru.dvvar.graduate.service.UserService;

/**
 * Created by Dmitriy_Varygin on 05.04.2016.
 */
public class UserController extends AbstractUserController {

    UserService service;

    public User create(User user) {
        return super.create(user);
    }

    public User get(int id) {
        return super.get(id);
    }

    public void update(User user) {
        super.update(user);
    }

    public void delete(int id) {
        super.delete(id);
    }
}
