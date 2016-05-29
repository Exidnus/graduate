package ru.dvvar.graduate.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.dvvar.graduate.model.Menu;
import ru.dvvar.graduate.model.Restaurant;
import ru.dvvar.graduate.model.User;
import ru.dvvar.graduate.repository.RestaurantRepository;
import ru.dvvar.graduate.repository.UserRepository;

import java.util.List;

/**
 * Created by Dmitriy_Varygin on 05.04.2016.
 */
@Service
public class RestaurantServiceImpl implements RestaurantService {

    private static final Logger LOG = LoggerFactory.getLogger(RestaurantServiceImpl.class);

    @Autowired
    private RestaurantRepository repository;

    @Autowired
    private UserRepository userRepository;

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
    @Transactional
    public void upvote(int menuId, int userId) {
        final User voter = userRepository.get(userId);
        voter.setMenuUpvoteId(menuId);
        userRepository.save(voter);
        final Menu upvoted = repository.getMenu(menuId);
        upvoted.upvote();
        repository.save(upvoted);
    }

    @Override
    @Transactional
    public void cancelUpvote(int userId) {
        final User voter = userRepository.get(userId);
        final Menu menu = repository.getMenu(voter.getMenuUpvoteId());
        menu.cancelUpvote();
        repository.save(menu);
        voter.setMenuUpvoteId(0);
        userRepository.save(voter);
    }

    @Override
    public Restaurant add(Restaurant restaurant) {
        return repository.save(restaurant);
    }

    @Override
    @Transactional
    public void addCurrentMenu(Menu menu, int restaurantId) {
        final Restaurant restaurant = repository.getWithAllMenus(restaurantId);
        repository.save(restaurant.changeCurrentMenu(menu));
    }

    @Override
    public boolean delete(int id) {
        return repository.delete(id);
    }

    @Override
    @Transactional
    public void updateMenu(Menu menu) {
        repository.save(menu);
    }

    @Override
    public boolean deleteMenu(int menuId) {
        return repository.deleteMenu(menuId);
    }

    @Override
    public Menu addMenu(Menu menuForSave) {
        return repository.save(menuForSave);
    }
}
