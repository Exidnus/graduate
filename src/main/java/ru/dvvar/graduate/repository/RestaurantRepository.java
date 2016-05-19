package ru.dvvar.graduate.repository;

import ru.dvvar.graduate.model.Menu;
import ru.dvvar.graduate.model.Restaurant;

import java.util.List;

/**
 * Created by Dmitriy_Varygin on 05.04.2016.
 */
public interface RestaurantRepository {

    Restaurant get(int id);

    Restaurant getWithAllMenus(int id);

    Restaurant save(Restaurant restaurant);

    boolean delete(int id);

    void update(Restaurant restaurant);

    List<Restaurant> getAll();

    Menu getMenu(int id);
}
