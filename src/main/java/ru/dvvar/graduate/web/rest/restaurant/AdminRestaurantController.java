package ru.dvvar.graduate.web.rest.restaurant;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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

    public Restaurant get(int id) {
        LOG.info("get " + id);
        return service.get(id);
    }

    public Restaurant getOneWithAllMenus(int id) {
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
