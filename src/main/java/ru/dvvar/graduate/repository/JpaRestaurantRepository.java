package ru.dvvar.graduate.repository;

import org.springframework.dao.support.DataAccessUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.dvvar.graduate.model.Menu;
import ru.dvvar.graduate.model.Restaurant;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by Dmitriy_Varygin on 19.05.2016.
 */
@Repository
@Transactional(readOnly = true)
public class JpaRestaurantRepository implements RestaurantRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Restaurant get(int id) {
        return em.find(Restaurant.class, id);
    }

    @Override
    @Transactional
    public void test(Menu menu, String newDescr) {
        Menu gotMenu = em.find(Menu.class, menu.getId());
        gotMenu.setDescription(newDescr);
    }

    @Override
    public List<Restaurant> getAll() {
        return em.createNamedQuery(Restaurant.GET_ALL, Restaurant.class).getResultList();
    }

    @Override
    public Restaurant getWithAllMenus(int id) {
        return DataAccessUtils.singleResult(em.createNamedQuery(Restaurant.GET_ONE_WITH_ALL_MENUS, Restaurant.class)
                .setParameter("id", id)
                .getResultList());
    }

    @Override
    public Menu getMenu(int id) {
        return em.find(Menu.class, id);
    }

    @Override
    @Transactional
    public Menu save(Menu menu) {
        if (menu.isNew()) {
            em.persist(menu);
            return menu;
        } else {
            em.createNativeQuery("DELETE FROM dishes d WHERE d.menu_id=:menuId")
                    .setParameter("menuId", menu.getId())
                    .executeUpdate();
            return em.merge(menu);
        }
    }

    @Override
    @Transactional
    public Restaurant save(Restaurant restaurant) {
        if (restaurant.isNew()) {
            em.persist(restaurant);
            return restaurant;
        } else {
            return em.merge(restaurant);
        }
    }

    @Override
    @Transactional
    public boolean deleteMenu(int menuId) {
        return em.createNamedQuery(Menu.DELETE)
                .setParameter("id", menuId)
                .executeUpdate() != 0;
    }

    @Override
    @Transactional
    public boolean delete(int id) {
        return em.createNamedQuery(Restaurant.DELETE)
                .setParameter("id", id)
                .executeUpdate() != 0;
    }

    @Override
    public void update(Restaurant restaurant) {

    }
}
