package ru.dvvar.graduate.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.dvvar.graduate.model.Menu;
import ru.dvvar.graduate.model.Restaurant;
import ru.dvvar.graduate.repository.RestaurantRepository;

import java.util.List;

/**
 * Created by Dmitriy_Varygin on 05.04.2016.
 */
@Service
public class RestaurantServiceImpl implements RestaurantService {

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Override
    public Restaurant get(int id) {
        return restaurantRepository.get(id);
    }

    @Override
    public Menu getMenu(int id) {
        return restaurantRepository.getMenu(id);
    }

    @Override
    public Restaurant save(Restaurant restaurant) {
        return restaurantRepository.save(restaurant);
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
    public Menu createOrUpdateCurrentMenu(Menu menu, int id, int userId) {
        return null;
    }

    @Override
    public void deleteCurrentMenu(int id, int userId) {

    }
}
