package ru.dvvar.graduate.service;

import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.dvvar.graduate.model.Menu;
import ru.dvvar.graduate.model.Restaurant;

import java.util.List;

import static ru.dvvar.graduate.RestaurantTestData.*;
import static ru.dvvar.graduate.UserTestData.ADMIN_ID;
import static ru.dvvar.graduate.UserTestData.USER_ID;

/**
 * Created by Dmitriy_Varygin on 19.05.2016.
 */
@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
public class RestaurantServiceTest extends TestCase {

    @Autowired
    private RestaurantService service;

    @Autowired
    private UserService userService;

    @Test
    public void shouldGet() throws Exception {
        RESTAURANT_MATCHER.assertEquals(RESTAURANT_1, service.get(RESTAURANT_ID_1));
    }

    @Test
    public void shouldGetAll() throws Exception {
        RESTAURANT_MATCHER.assertListsEquals(RESTAURANTS, service.getAll());
    }

    @Test
    public void shouldGetOneWithAllMenus() throws Exception {
        final Restaurant restaurantWithAllMenus = service.getWithAllMenus(RESTAURANT_ID_1);
        RESTAURANT_MATCHER.assertEquals(RESTAURANT_1, restaurantWithAllMenus);
        MENU_MATCHER.assertListsEquals(RESTAURANT_1.getMenus(), restaurantWithAllMenus.getMenus());
    }


    @Test
    public void shouldDelete() throws Exception {
        assertTrue(service.delete(RESTAURANT_ID_1));
        List<Restaurant> all = service.getAll();
        assertEquals(TWO_RESTAURANTS_EXCEPT_FIRST, all);
    }

    @Test
    public void shouldNotDelete() throws Exception {
        assertFalse(service.delete(1));
    }

    @Test
    public void shouldGetMenu() throws Exception {
        Menu menu = service.getMenu(MENU_ID_1);
        assertTrue(menu != null);
        System.out.println(menu);
    }

    @Test
    public void shouldUpvote() throws Exception {
        service.upvote(MENU_ID_2, USER_ID);
        assertEquals(MENU_2.getCurrentUpvotes() + 1, service.getMenu(MENU_ID_2).getCurrentUpvotes());
        assertEquals(MENU_ID_2, userService.get(USER_ID).getMenuUpvotedId());
    }

    @Test
    public void shouldCancelUpvote() throws Exception {
        service.cancelUpvote(ADMIN_ID);
        assertEquals(MENU_3.getCurrentUpvotes() - 1, service.getMenu(MENU_ID_3).getCurrentUpvotes());
        assertEquals(0, userService.get(ADMIN_ID).getMenuUpvotedId());
    }

    @Test
    public void shouldAddRestaurant() throws Exception {
        Restaurant forSave = new Restaurant(RESTAURANT_FOR_SAVE);
        RESTAURANT_MATCHER.assertEquals(forSave, service.add(forSave));
    }

    /*@Test
    public void shouldAddMenu() throws Exception {
        final Menu forSave = new Menu(MENU_FOR_SAVE);
        final Menu saved = service.addMenu(forSave);
        //forSave.setId(saved.getId());
        MENU_MATCHER.assertEquals(forSave, saved);
        System.out.println(forSave);
        System.out.println(saved);
    }*/

    @Test
    public void shouldAddCurrentMenu() throws Exception {
        service.addCurrentMenu(MENU_FOR_SAVE, RESTAURANT_ID_1);
        final Restaurant restaurantWithNewCurrentMenu = service.getWithAllMenus(RESTAURANT_ID_1);
        assertTrue(restaurantWithNewCurrentMenu.getMenus().size() == 3);
        final Menu newCurrentMenu = restaurantWithNewCurrentMenu.getCurrentMenu();
        MENU_FOR_SAVE.setId(newCurrentMenu.getId());
        for (int i = 0; i < newCurrentMenu.getDishes().size(); i++) {
            MENU_FOR_SAVE.getDishes().get(i).setId(newCurrentMenu.getDishes().get(i).getId());
        }
        MENU_MATCHER.assertEquals(MENU_FOR_SAVE, newCurrentMenu);
    }

    @Test
    public void shouldUpdateMenu() throws Exception {
        service.updateMenu(UPDATED_MENU);
        Restaurant restaurant = service.get(RESTAURANT_ID_2);
        UPDATED_MENU.getDishes().get(0).setId(restaurant.getCurrentMenu().getDishes().get(0).getId());
        MENU_MATCHER.assertEquals(UPDATED_MENU, restaurant.getCurrentMenu());
    }

    @Test
    public void shouldUpdateMenuFromMenuTo() throws Exception {
        service.updateMenuFromMenuTo(MENU_TO_1);
    }

    @Test
    public void shouldDeleteMenu() throws Exception {
        assertTrue(service.deleteMenu(MENU_ID_3));
    }
}
