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
    private RestaurantRepository repository;

    @Override
    public Restaurant get(int id) {
        return repository.get(id);
    }

    @Override
    public Menu getMenu(int id) {
        return repository.getMenu(id);
    }

    @Override
    public Restaurant save(Restaurant restaurant) {
        return repository.save(restaurant);
    }

    @Override
    public List<Restaurant> getAll() {
        return repository.getAll();
    }

    @Override
    public Restaurant getWithAllMenus(int id) {
        return repository.getWithAllMenus(id);
    }

    @Override
    public void upvote(int id, int userId) {

    }

    @Override
    public void cancelUpvote(int userId) {

    }

    @Override
    public void update(Restaurant restaurant, int userId) {
        repository.update(restaurant);
    }

    @Override
    public boolean delete(int id) {
        return repository.delete(id);
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
