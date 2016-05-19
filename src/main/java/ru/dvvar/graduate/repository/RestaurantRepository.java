package ru.dvvar.graduate.repository;

import ru.dvvar.graduate.model.Restaurant;

import java.util.List;

/**
 * Created by Dmitriy_Varygin on 05.04.2016.
 */
public interface RestaurantRepository {

    Restaurant get(int id);

    Restaurant save(Restaurant restaurant);

    void delete(int id);

    void update(Restaurant restaurant);

    List<Restaurant> getAll();
}
