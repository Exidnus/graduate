package ru.dvvar.graduate.service;

import ru.dvvar.graduate.model.GeneralStatistics;
import ru.dvvar.graduate.model.Menu;
import ru.dvvar.graduate.model.Restaurant;
import ru.dvvar.graduate.model.Statistics;
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
    public List<Restaurant> getAll() {
        return restaurantRepository.getAll();
    }

    @Override
    public Restaurant getWithAllMenus(int id) {
        return null;
    }

    @Override
    public void upvote(int id, int userId) {

    }

    @Override
    public void cancelUpvote(int userId) {

    }

    @Override
    public void update(Restaurant restaurant, int userId) {
        restaurantRepository.update(restaurant);
    }

    @Override
    public void delete(int id, int userId) {

    }

    @Override
    public Restaurant add(Restaurant restaurant, int userId) {
        return null;
    }

    @Override
    public void createOrUpdateCurrentMenu(Menu menu, int id, int userId) {

    }

    @Override
    public void deleteCurrentMenu(int id, int userId) {

    }

    @Override
    public Statistics getStatisticForOne(int id, int userId) {
        return null;
    }

    @Override
    public List<Statistics> getStatisticsForAll(int userId) {
        return null;
    }

    @Override
    public GeneralStatistics getGeneralStatistics(int userId) {
        return null;
    }

    @Override
    public void deleteMenuFromHistory(int menuId, int id, int userId) {

    }
}
