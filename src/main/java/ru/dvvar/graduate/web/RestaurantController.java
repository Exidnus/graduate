package ru.dvvar.graduate.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.dvvar.graduate.model.Restaurant;
import ru.dvvar.graduate.service.RestaurantService;

import java.util.List;

/**
 * Created by Dmitriy_Varygin on 05.04.2016.
 */
public class RestaurantController {

    private static final Logger LOG = LoggerFactory.getLogger(RestaurantController.class);

    private RestaurantService service;

    public Restaurant create(Restaurant restaurant) {
        restaurant.setId(null);
        LOG.info("create " + restaurant);
        return service.save(restaurant);
    }

    public Restaurant get(int id) {
        LOG.info("get " + id);
        return service.get(id);
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
