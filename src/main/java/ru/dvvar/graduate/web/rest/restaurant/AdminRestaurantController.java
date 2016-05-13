package ru.dvvar.graduate.web.rest.restaurant;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.dvvar.graduate.model.Restaurant;
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

    public Restaurant getWithAllMenus(int id) {
        LOG.info("get {} with all menus", id);
        return service.getWithAllMenus(id);
    }

    public Restaurant create(Restaurant restaurant) {
        restaurant.setId(null);
        LOG.info("create " + restaurant);
        return service.save(restaurant);
    }

    public void delete(int id) {
        LOG.info("delete " + id);
        service.delete(id);
    }

    public void update(Restaurant restaurant) {
        LOG.info("update " + restaurant);
        service.update(restaurant);
    }

    public List<Restaurant> getAll() {
        LOG.info("getAll");
        return service.getAll();
    }
}
