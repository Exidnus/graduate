package ru.dvvar.graduate.service;

import ru.dvvar.graduate.model.GeneralStatistics;
import ru.dvvar.graduate.model.Menu;
import ru.dvvar.graduate.model.Restaurant;
import ru.dvvar.graduate.model.Statistics;

import java.util.List;

/**
 * Created by Dmitriy_Varygin on 05.04.2016.
 */
public interface RestaurantService {

    Restaurant get(int id);

    Menu getMenu(int id);

    List<Restaurant> getAll();

    Restaurant save(Restaurant restaurant);

    Restaurant add(Restaurant restaurant);

    default void update(Restaurant restaurant) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    boolean delete(int id);

    Restaurant getWithAllMenus(int id);

    void upvote(int menuId, int userId);

    void cancelUpvote(int userId);

    void addCurrentMenu(Menu menu, int restaurantId);

    void updateMenu(Menu menu);

    boolean deleteMenu(int menuId);

    default Statistics getStatisticForOne(int id, int userId) {
        throw new UnsupportedOperationException();
    }

    default List<Statistics> getStatisticsForAll(int userId) {
        throw new UnsupportedOperationException();
    }

    default GeneralStatistics getGeneralStatistics(int userId)  {
        throw new UnsupportedOperationException();
    }

    Menu addMenu(Menu menuForSave);
}
