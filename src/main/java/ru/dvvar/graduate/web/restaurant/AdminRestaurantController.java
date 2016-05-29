package ru.dvvar.graduate.web.restaurant;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.dvvar.graduate.model.GeneralStatistics;
import ru.dvvar.graduate.model.Menu;
import ru.dvvar.graduate.model.Restaurant;
import ru.dvvar.graduate.model.Statistics;
import ru.dvvar.graduate.service.RestaurantService;
import ru.dvvar.graduate.web.LoggedUser;

import java.net.URI;
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
    public ResponseEntity<Restaurant> createWithLocation(@RequestBody Restaurant restaurant) {

        final Restaurant created = service.add(restaurant);

        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(UserRestaurantController.REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();

        LOG.info("User with id {} added restaurant {}", LoggedUser.getId(), created);

        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    //TODO make tests for service and web
    @RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void update(@RequestBody Restaurant restaurant) {
        LOG.info("User with id {} updated restaurant {}", LoggedUser.getId(), restaurant);
        service.update(restaurant);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable int id) {
        LOG.info("User with id {} deleted restaurant with id {}", LoggedUser.getId(), id);
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

    @RequestMapping(value = "/menus", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void updateMenu(@RequestBody Menu menu) {
        LOG.info("User with id {} updated current menu {}", LoggedUser.getId(), menu);
        service.updateMenu(menu);
    }

    @RequestMapping(value = "/menus/{id}", method = RequestMethod.DELETE)
    public void deleteMenu(@PathVariable int id) {
        LOG.info("User with id {} deleted current menu from restaurant with id {}", LoggedUser.getId(), id);
        service.deleteMenu(id);
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
