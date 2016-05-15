package ru.dvvar.graduate.web.restaurant;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.dvvar.graduate.model.Restaurant;
import ru.dvvar.graduate.service.RestaurantService;

import java.util.List;

/**
 * Created by Dmitriy_Varygin on 05.04.2016.
 */
@RestController
@RequestMapping(UserRestaurantController.REST_URL)
public class UserRestaurantController {

    static final String REST_URL = "/restaurants";

    @Autowired
    private RestaurantService service;

    private static final Logger LOG = LoggerFactory.getLogger(UserRestaurantController.class);

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Restaurant get(@PathVariable int id) {
        LOG.info("get " + id);
        return service.get(id);
    }

    @RequestMapping(value = "/history/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Restaurant getOneWithAllMenus(@PathVariable int id) {
        LOG.info("get {} with all menus", id);
        return service.getWithAllMenus(id);
    }

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Restaurant> getAllWithCurrentMenus() {
        LOG.info("getAll");
        return service.getAll();
    }

    @RequestMapping(value = "/upvote/{id}", method = RequestMethod.PUT)
    public void upvote(@PathVariable int id, @RequestParam int userId) {
        LOG.info("vote for restaurant with id {} from user with id {}", id, userId);
        service.upvote(id, userId);
    }

    @RequestMapping(value = "/upvote/{userId}", method = RequestMethod.DELETE)
    public void cancel(@PathVariable int userId) {
        LOG.info("cancel vote for user with id {}", userId);
        service.cancelUpvote(userId);
    }
}
