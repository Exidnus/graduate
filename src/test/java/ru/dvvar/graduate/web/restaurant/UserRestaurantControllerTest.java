package ru.dvvar.graduate.web.restaurant;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import ru.dvvar.graduate.model.Restaurant;
import ru.dvvar.graduate.service.RestaurantService;
import ru.dvvar.graduate.service.UserService;
import ru.dvvar.graduate.web.AbstractControllerTest;

import static junit.framework.TestCase.assertEquals;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.dvvar.graduate.RestaurantTestData.*;
import static ru.dvvar.graduate.UserTestData.*;
import static ru.dvvar.graduate.web.restaurant.UserRestaurantController.REST_URL;

/**
 * Created by Dmitriy_Varygin on 24.05.2016.
 */
public class UserRestaurantControllerTest extends AbstractControllerTest {

    @Autowired
    private RestaurantService service;

    @Autowired
    private UserService userService;

    @Test
    public void shouldGetOneWithCurrentMenu() throws Exception {
        mockMvc.perform(get(REST_URL + "/" + RESTAURANT_ID_1)
                .with(httpBasic(USUAL_USER.getEmail(), USUAL_USER.getPassword())))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(RESTAURANT_MATCHER.isContentMatch(RESTAURANT_1));
    }

    @Test
    public void shouldGetAllWithCurrentsMenus() throws Exception {
        mockMvc.perform(get(REST_URL)
                .with(httpBasic(USUAL_USER.getEmail(), USUAL_USER.getPassword())))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(RESTAURANT_MATCHER.isContentListMatch(RESTAURANTS));
    }

    @Test
    public void shouldGetOneWithAllMenus() throws Exception {
        final ResultActions actions = mockMvc.perform(get(REST_URL + "/history/" + RESTAURANT_ID_1)
                .with(httpBasic(USUAL_USER.getEmail(), USUAL_USER.getPassword())))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(RESTAURANT_MATCHER.isContentMatch(RESTAURANT_1));

        final Restaurant restaurantWithHistory = RESTAURANT_MATCHER.fromJsonAction(actions);
        MENU_MATCHER.assertListsEquals(restaurantWithHistory.getMenus(), RESTAURANT_1.getMenus());
    }

    @Test
    public void shouldUpvote() throws Exception {
        mockMvc.perform(post(REST_URL + "/upvote/" + MENU_ID_2)
                .with(httpBasic(USUAL_USER.getEmail(), USUAL_USER.getPassword())))
                .andDo(print())
                .andExpect(status().isOk());

        assertEquals(MENU_2.getCurrentUpvotes() + 1, service.getMenu(MENU_ID_2).getCurrentUpvotes());
        assertEquals(MENU_ID_2, userService.get(USER_ID).getMenuUpvotedId());
    }

    @Test
    public void shouldCancelUpvote() throws Exception {
        mockMvc.perform(delete(REST_URL + "/upvote")
                .with(httpBasic(ADMIN.getEmail(), ADMIN.getPassword())))
                .andDo(print())
                .andExpect(status().isOk());

        assertEquals(MENU_3.getCurrentUpvotes() - 1, service.getMenu(MENU_ID_3).getCurrentUpvotes());
        assertEquals(0, userService.get(ADMIN_ID).getMenuUpvotedId());
    }
}
