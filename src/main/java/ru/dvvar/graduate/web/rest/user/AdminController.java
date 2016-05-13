package ru.dvvar.graduate.web.rest.user;

import ru.dvvar.graduate.model.User;

import java.util.List;

/**
 * Created by Dmitriy_Varygin on 13.05.2016.
 */
public class AdminController extends AbstractUserController {

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

    public List<User> getAll() {
        return super.getAll();
    }
}
