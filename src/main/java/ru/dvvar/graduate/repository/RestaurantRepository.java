package ru.dvvar.graduate.repository;

import ru.dvvar.graduate.model.Restaurant;

import java.util.List;

/**
 * Created by Dmitriy_Varygin on 05.04.2016.
 */
public interface RestaurantRepository {
    Restaurant save(Restaurant restaurant);

    Restaurant get(int id);

    void delete(int id);

    void update(Restaurant restaurant);

    List<Restaurant> getAll();
}
