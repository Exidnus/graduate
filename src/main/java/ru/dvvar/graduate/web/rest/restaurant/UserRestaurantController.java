package ru.dvvar.graduate.web.rest.restaurant;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.dvvar.graduate.model.Restaurant;
import ru.dvvar.graduate.model.Vote;
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

    public Restaurant get(int id) {
        LOG.info("get " + id);
        return service.get(id);
    }

    public Restaurant getWithAllMenus(int id) {
        LOG.info("get {} with all menus", id);
        return service.getWithAllMenus(id);
    }

    public List<Restaurant> getAllWithCurrentMenus() {
        LOG.info("getAll");
        return service.getAll();
    }

    public void upvote(int id, int userId) {
        LOG.info("vote for restaurant with id {} from user with id {}", id, userId);
        service.upvote(id, userId);
    }

    public void cancel(int userId) {
        LOG.info("cancel vote for user with id {}", userId);
        service.cancelUpvote(userId);
    }
}
