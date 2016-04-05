package ru.dvvar.graduate.service;

import ru.dvvar.graduate.model.Menu;
import ru.dvvar.graduate.model.Restaurant;
import ru.dvvar.graduate.repository.RestaurantRepository;

import java.util.List;

/**
 * Created by Dmitriy_Varygin on 05.04.2016.
 */
public class RestaurantServiceImpl implements RestaurantService {

    RestaurantRepository restaurantRepository;

    @Override
    public Restaurant save(Restaurant restaurant) {
        return restaurantRepository.save(restaurant);
    }

    @Override
    public Restaurant get(int id) {
        return restaurantRepository.get(id);
    }

    @Override
    public void delete(int id) {
        restaurantRepository.delete(id);
    }

    @Override
    public void update(Restaurant restaurant) {
        restaurantRepository.update(restaurant);
    }

    @Override
    public List<Restaurant> getAll() {
        return restaurantRepository.getAll();
    }

}
