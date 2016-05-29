package ru.dvvar.graduate.web.user;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import ru.dvvar.graduate.service.UserService;
import ru.dvvar.graduate.web.AbstractControllerTest;

import java.util.Collections;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.dvvar.graduate.UserTestData.*;
import static ru.dvvar.graduate.web.user.AdminController.REST_URL;

/**
 * Created by Dmitriy_Varygin on 24.05.2016.
 */
public class AdminControllerTest extends AbstractControllerTest {

    @Autowired
    private UserService service;

    @Test
    public void notAdminsShouldNotBeAblePerformAdminsActions() throws Exception {
        mockMvc.perform(get(REST_URL)
                .with(httpBasic(USUAL_USER.getEmail(), USUAL_USER.getPassword())))
                .andExpect(status().isForbidden());
    }

    @Test
    public void adminShouldDeleteUser() throws Exception {
        mockMvc.perform(delete(REST_URL + "/" + USUAL_USER.getId())
                .with(httpBasic(ADMIN.getEmail(), ADMIN.getPassword())))
                .andDo(print())
                .andExpect(status().isOk());

        MATCHER.assertListsEquals(Collections.singletonList(ADMIN), service.getAll());
    }

    @Test
    public void shouldGetAll() throws Exception {
        mockMvc.perform(get(REST_URL)
                .with(httpBasic(ADMIN.getEmail(), ADMIN.getPassword())))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MATCHER.isContentListMatch(USERS_ORDERED_BY_NAME));
    }
}
