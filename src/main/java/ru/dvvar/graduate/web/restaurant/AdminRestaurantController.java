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
import ru.dvvar.graduate.web.LoggedUser;

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

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Restaurant add(@RequestBody Restaurant restaurant) {
        final int userId = LoggedUser.getId();
        LOG.info("User with id {} added restaurant {}", userId, restaurant);
        return service.add(restaurant);
    }

    @RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public void update(@RequestBody Restaurant restaurant) {
        final int userId = LoggedUser.getId();
        LOG.info("User with id {} updated restaurant {}", userId, restaurant);
        service.update(restaurant);
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public void delete(int id) {
        final int userId = LoggedUser.getId();
        LOG.info("User with id {} deleted restaurant with id {}", userId, id);
        service.delete(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Menu createCurrentMenu(@RequestBody Menu menu, @PathVariable int id) {
        final int userId = LoggedUser.getId();
        LOG.info("User with id {} added current menu {} for restaurant with id {}",
                userId, menu, id);
        //return service.updateMenu(menu, id);
        return menu;
    }

    @RequestMapping(value = "/{id}/{menuId}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void updateCurrentMenu(@RequestBody Menu menu, @PathVariable int id, @PathVariable int menuId) {
        final int userId = LoggedUser.getId();
        LOG.info("User with id {} updated current menu {} for restaurant with id {}",
                userId, menu, id);
        service.updateMenu(menu);
    }

    @RequestMapping(value = "/{id}/menu", method = RequestMethod.DELETE)
    public void deleteCurrentMenu(@PathVariable int id) {
        final int userId = LoggedUser.getId();
        LOG.info("User with id {} deleted current menu from restaurant with id {}", userId, id);
        service.deleteMenu(id);
    }

    @RequestMapping(value = "/{id}/history/{menuId}", method = RequestMethod.DELETE)
    public void deleteMenuFromHistory(@PathVariable int id, @PathVariable int menuId) {
        final int userId = LoggedUser.getId();
        LOG.info("User with id {} deleted menu with id {} from history of restaurant with id {}",
                userId, menuId, userId);
        service.deleteMenuFromHistory(menuId, id, userId);
    }

    @RequestMapping(value = "/statistics/{id}", method = RequestMethod.GET)
    public Statistics getStatisticForOne(@PathVariable int id) {
        final int userId = LoggedUser.getId();
        LOG.info("User with id {} got statistic for restaurant with id {}", id, userId);
        return service.getStatisticForOne(id, userId);
    }

    @RequestMapping(value = "/statistics", method = RequestMethod.GET)
    public List<Statistics> getStatisticsForAll() {
        final int userId = LoggedUser.getId();
        LOG.info("User with id {} got statistics for all restaurants", userId);
        return service.getStatisticsForAll(userId);
    }

    @RequestMapping(value = "/statistics/general", method = RequestMethod.GET)
    public GeneralStatistics getGeneralStatistics() {
        final int userId = LoggedUser.getId();
        LOG.info("User with id {} got general statistics", userId);
        return service.getGeneralStatistics(userId);
    }
}
