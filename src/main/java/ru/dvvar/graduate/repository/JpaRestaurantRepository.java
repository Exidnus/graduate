package ru.dvvar.graduate.repository;

import org.springframework.stereotype.Repository;
import ru.dvvar.graduate.model.Menu;
import ru.dvvar.graduate.model.Restaurant;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by Dmitriy_Varygin on 19.05.2016.
 */
@Repository
public class JpaRestaurantRepository implements RestaurantRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Restaurant get(int id) {
        return em.find(Restaurant.class, id);
    }

    @Override
    public Menu getMenu(int id) {
        return em.find(Menu.class, id);
    }

    @Override
    public Restaurant save(Restaurant restaurant) {
        return null;
    }

    @Override
    public void delete(int id) {

    }

    @Override
    public void update(Restaurant restaurant) {

    }

    @Override
    public List<Restaurant> getAll() {
        return null;
    }
}
