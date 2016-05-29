package ru.dvvar.graduate.web.restaurant;

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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.dvvar.graduate.RestaurantTestData.*;
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
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(RestaurantTestData.RESTAURANT_FOR_SAVE)))
                .andDo(print());

        final Restaurant saved = RESTAURANT_MATCHER.fromJsonAction(actions);
        RESTAURANT_FOR_SAVE.setId(saved.getId());
        RESTAURANT_FOR_SAVE.getCurrentMenu().setId(saved.getCurrentMenu().getId());
        RESTAURANT_FOR_SAVE.getCurrentMenu().getDishes().get(0).setId(saved.getCurrentMenu().getDishes().get(0).getId());
        RESTAURANT_FOR_SAVE.getCurrentMenu().getDishes().get(1).setId(saved.getCurrentMenu().getDishes().get(1).getId());
        RESTAURANT_MATCHER.assertEquals(saved, RESTAURANT_FOR_SAVE);
        RESTAURANT_MATCHER.assertEquals(service.get(saved.getId()), RESTAURANT_FOR_SAVE);
    }

    @Test
    public void shouldDeleteRestaurant() throws Exception {
        mockMvc.perform(delete(REST_URL)
                .param("id", String.valueOf(RESTAURANT_ID_1)))
                .andDo(print())
                .andExpect(status().isOk());
        RESTAURANT_MATCHER.assertListsEquals(TWO_RESTAURANTS_EXCEPT_FIRST, service.getAll());
    }

    @Test
    public void shouldUpdateMenu() throws Exception {
        mockMvc.perform(put(REST_URL + "/menus")
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
        mockMvc.perform(delete(REST_URL + "/menus/" + MENU_ID_3))
                .andDo(print())
                .andExpect(status().isOk());
        assertNull(service.getMenu(MENU_ID_3));
    }

}
