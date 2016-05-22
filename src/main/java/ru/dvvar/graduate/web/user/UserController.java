package ru.dvvar.graduate.web.user;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.dvvar.graduate.model.User;
import ru.dvvar.graduate.web.LoggedUser;

import java.net.URI;

/**
 * Created by Dmitriy_Varygin on 05.04.2016.
 */
@RestController
@RequestMapping(UserController.REST_URL)
public class UserController extends AbstractUserController {

    static final String REST_URL = "/profile";

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public User get() {
        return super.get(LoggedUser.getId());
    }

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> createWithLocation(User user) {
        final User created = super.create(user);

        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();

        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void update(User user) {
        super.update(user, LoggedUser.getId());
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public void delete() {
        super.delete(LoggedUser.getId());
    }
}
