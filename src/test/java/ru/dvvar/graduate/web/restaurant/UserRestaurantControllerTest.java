package ru.dvvar.graduate.web.restaurant;

import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import ru.dvvar.graduate.model.Restaurant;
import ru.dvvar.graduate.web.AbstractControllerTest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.dvvar.graduate.RestaurantTestData.*;
import static ru.dvvar.graduate.web.restaurant.UserRestaurantController.REST_URL;

/**
 * Created by Dmitriy_Varygin on 24.05.2016.
 */
public class UserRestaurantControllerTest extends AbstractControllerTest {

    @Test
    public void shouldGetOneWithCurrentMenu() throws Exception {
        mockMvc.perform(get(REST_URL + "/" + RESTAURANT_ID_1))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(RESTAURANT_MATCHER.isContentMatch(RESTAURANT_1));
    }

    @Test
    public void shouldGetAllWithCurrentsMenus() throws Exception {
        mockMvc.perform(get(REST_URL))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(RESTAURANT_MATCHER.isContentListMatch(RESTAURANTS));
    }

    @Test
    public void shouldGetOneWithAllMenus() throws Exception {
        final ResultActions actions = mockMvc.perform(get(REST_URL + "/history/" + RESTAURANT_ID_1))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(RESTAURANT_MATCHER.isContentMatch(RESTAURANT_1));

        final Restaurant restaurantWithHistory = RESTAURANT_MATCHER.fromJsonAction(actions);
        MENU_MATCHER.assertListsEquals(restaurantWithHistory.getMenus(), RESTAURANT_1.getMenus());
    }

    //TODO tests on voting
}
