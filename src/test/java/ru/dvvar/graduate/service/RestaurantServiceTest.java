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

import static ru.dvvar.graduate.RestaurantTestData.*;
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
        service.upvote(MENU_ID_1, USER_ID);
        assertEquals(MENU_1.getCurrentUpvotes() + 1, service.getMenu(MENU_ID_1).getCurrentUpvotes());
        assertEquals(MENU_ID_1, userService.get(USER_ID).getMenuUpvoteId());
    }

    @Test
    public void shouldCancelUpvote() throws Exception {
        service.cancelUpvote(USER_ID);
    }
}
