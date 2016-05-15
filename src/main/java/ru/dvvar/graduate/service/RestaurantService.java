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

    List<Restaurant> getAll();

    Restaurant save(Restaurant restaurant);

    void delete(int id, int userId);

    void update(Restaurant restaurant, int userId);

    Restaurant getWithAllMenus(int id);

    void upvote(int id, int userId);

    void cancelUpvote(int userId);

    Restaurant add(Restaurant restaurant, int userId);

    Menu createOrUpdateCurrentMenu(Menu menu, int id, int userId);

    void deleteCurrentMenu(int id, int userId);

    default void deleteMenuFromHistory(int menuId, int id, int userId) {
        throw new UnsupportedOperationException();
    }

    default Statistics getStatisticForOne(int id, int userId) {
        throw new UnsupportedOperationException();
    }

    default List<Statistics> getStatisticsForAll(int userId) {
        throw new UnsupportedOperationException();
    }

    default GeneralStatistics getGeneralStatistics(int userId)  {
        throw new UnsupportedOperationException();
    }
}
