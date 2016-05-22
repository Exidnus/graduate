package ru.dvvar.graduate.web.user;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import ru.dvvar.graduate.service.UserService;
import ru.dvvar.graduate.web.AbstractControllerTest;

import java.util.Collections;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.dvvar.graduate.UserTestData.ADMIN;
import static ru.dvvar.graduate.UserTestData.MATCHER;
import static ru.dvvar.graduate.UserTestData.USUAL_USER;
import static ru.dvvar.graduate.web.user.UserController.REST_URL;

/**
 * Created by Dmitriy_Varygin on 22.05.2016.
 */
public class UserControllerTest extends AbstractControllerTest {

    @Autowired
    private UserService service;

    @Test
    public void shouldGet() throws Exception {
        mockMvc.perform(get(REST_URL))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MATCHER.isContentMatch(USUAL_USER));
    }

    @Test
    public void shouldCreate() throws Exception {

    }

    @Test
    public void shouldUpdate() throws Exception {

    }

    @Test
    public void shouldDelete() throws Exception {
        mockMvc.perform(delete(REST_URL))
                .andDo(print())
                .andExpect(status().isOk());
        MATCHER.assertListsEquals(Collections.singletonList(ADMIN), service.getAll());
    }
}
