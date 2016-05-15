package ru.dvvar.graduate.web.restaurant;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.dvvar.graduate.model.GeneralStatistics;
import ru.dvvar.graduate.model.Menu;
import ru.dvvar.graduate.model.Restaurant;
import ru.dvvar.graduate.model.Statistics;
import ru.dvvar.graduate.service.RestaurantService;

import java.util.List;

/**
 * Created by Dmitriy_Varygin on 05.04.2016.
 */
@RestController
@RequestMapping(AdminRestaurantController.REST_URL)
public class AdminRestaurantController {

    static final String REST_URL = "/admin/restaurants";

    private static final Logger LOG = LoggerFactory.getLogger(AdminRestaurantController.class);

    @Autowired
    private RestaurantService service;

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

    public Restaurant add(Restaurant restaurant, int userId) {
        LOG.info("User with id {} added restaurant {}", userId, restaurant);
        return service.add(restaurant, userId);
    }

    public void update(Restaurant restaurant, int userId) {
        LOG.info("User with id {} updated restaurant {}", userId, restaurant);
        service.update(restaurant, userId);
    }

    public void delete(int id, int userId) {
        LOG.info("User with id {} deleted restaurant with id {}", userId, id);
        service.delete(id, userId);
    }

    public void createOrChangeCurrentMenu(Menu menu, int id, int userId) {
        LOG.info("User with id {} updated current menu {} for restaurant with id {}",
                userId, menu, id);
        service.createOrUpdateCurrentMenu(menu, id, userId);
    }

    public void deleteCurrentMenu(int id, int userId) {
        LOG.info("User with id {} deleted current menu from restaurant with id {}");
        service.deleteCurrentMenu(id, userId);
    }

    public void deleteMenuFromHistory(int menuId, int id, int userId) {
        LOG.info("User with id {} deleted menu with id {} from history of restaurant with id {}",
                userId, menuId, userId);
        service.deleteMenuFromHistory(menuId, id, userId);
    }

    public Statistics getStatisticForOne(int id, int userId) {
        LOG.info("User with id {} got statistic for restaurant with id {}", id, userId);
        return service.getStatisticForOne(id, userId);
    }

    public List<Statistics> getStatisticsForAll(int userId) {
        LOG.info("User with id {} got statistics for all restaurants", userId);
        return service.getStatisticsForAll(userId);
    }

    public GeneralStatistics getGeneralStatistics(int userId) {
        LOG.info("User with id {} got general statistics", userId);
        return service.getGeneralStatistics(userId);
    }
}
