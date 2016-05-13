package ru.dvvar.graduate.web.rest.restaurant;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.dvvar.graduate.model.Vote;

/**
 * Created by Dmitriy_Varygin on 05.04.2016.
 */
@RestController
@RequestMapping(UserRestaurantController.REST_URL)
public class UserRestaurantController {

    static final String REST_URL = "/restaurants";

    private static final Logger LOG = LoggerFactory.getLogger(UserRestaurantController.class);

    public void upvote(Vote vote, int userId) {
        LOG.info("vote {} user with id {}", vote, userId);
    }

    public void cancel(Vote vote, int userId) {
        LOG.info("cancel {} user with id {}", vote, userId);
    }
}
