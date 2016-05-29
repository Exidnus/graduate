package ru.dvvar.graduate.web.restaurant;

import junit.framework.TestCase;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import ru.dvvar.graduate.RestaurantTestData;
import ru.dvvar.graduate.model.Restaurant;
import ru.dvvar.graduate.service.RestaurantService;
import ru.dvvar.graduate.util.json.JsonUtil;
import ru.dvvar.graduate.web.AbstractControllerTest;

import static junit.framework.TestCase.assertNull;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.dvvar.graduate.RestaurantTestData.*;
import static ru.dvvar.graduate.UserTestData.ADMIN;
import static ru.dvvar.graduate.web.restaurant.AdminRestaurantController.REST_URL;

/**
 * Created by Dmitriy_Varygin on 24.05.2016.
 */
public class AdminRestaurantControllerTest extends AbstractControllerTest {

    @Autowired
    private RestaurantService service;

    @Test
    public void shouldAddRestaurant() throws Exception {
        ResultActions actions = mockMvc.perform(post(REST_URL)
                .with(httpBasic(ADMIN.getEmail(), ADMIN.getPassword()))
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(RestaurantTestData.RESTAURANT_FOR_SAVE)))
                .andDo(print());

        final Restaurant saved = RESTAURANT_MATCHER.fromJsonAction(actions);
        RESTAURANT_FOR_SAVE.setId(saved.getId());
        RESTAURANT_FOR_SAVE.getCurrentMenu().setId(saved.getCurrentMenu().getId());
        RESTAURANT_FOR_SAVE.getCurrentMenu().getDishes().get(0).setId(saved.getCurrentMenu().getDishes().get(0).getId());
        RESTAURANT_FOR_SAVE.getCurrentMenu().getDishes().get(1).setId(saved.getCurrentMenu().getDishes().get(1).getId());
        RESTAURANT_MATCHER.assertEquals(saved, RESTAURANT_FOR_SAVE);
        final Restaurant savedFromDB = service.getWithAllMenus(saved.getId());
        RESTAURANT_MATCHER.assertEquals(savedFromDB, RESTAURANT_FOR_SAVE);
        TestCase.assertEquals(savedFromDB.getMenus().toString(), RESTAURANT_FOR_SAVE.getMenus().toString());
        //MENU_MATCHER.assertListsEquals(savedFromDB.getMenus(), RESTAURANT_FOR_SAVE.getMenus());
        //TODO imagine why MENU_MATCHER doesn't assert list's equals
    }

    @Test
    public void shouldDeleteRestaurant() throws Exception {
        mockMvc.perform(delete(REST_URL + "/" + RESTAURANT_ID_1)
                .with(httpBasic(ADMIN.getEmail(), ADMIN.getPassword())))
                .andDo(print())
                .andExpect(status().isOk());
        RESTAURANT_MATCHER.assertListsEquals(TWO_RESTAURANTS_EXCEPT_FIRST, service.getAll());
    }

    @Test
    public void shouldUpdateMenu() throws Exception {
        mockMvc.perform(put(REST_URL + "/menus")
                .with(httpBasic(ADMIN.getEmail(), ADMIN.getPassword()))
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(UPDATED_MENU)))
                .andDo(print())
                .andExpect(status().isOk());

        final Restaurant restaurant = service.get(RESTAURANT_ID_2);
        UPDATED_MENU.getDishes().get(0).setId(restaurant.getCurrentMenu().getDishes().get(0).getId());
        MENU_MATCHER.assertEquals(UPDATED_MENU, restaurant.getCurrentMenu());
    }

    @Test
    public void shouldDeleteMenu() throws Exception {
        mockMvc.perform(delete(REST_URL + "/menus/" + MENU_ID_3)
                .with(httpBasic(ADMIN.getEmail(), ADMIN.getPassword())))
                .andDo(print())
                .andExpect(status().isOk());
        assertNull(service.getMenu(MENU_ID_3));
    }

}
