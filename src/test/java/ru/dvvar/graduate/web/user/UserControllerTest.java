package ru.dvvar.graduate.web.user;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import ru.dvvar.graduate.model.Role;
import ru.dvvar.graduate.model.User;
import ru.dvvar.graduate.service.UserService;
import ru.dvvar.graduate.util.json.JsonUtil;
import ru.dvvar.graduate.web.AbstractControllerTest;

import java.util.Collections;
import java.util.EnumSet;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.dvvar.graduate.UserTestData.*;
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
        User expected = new User("Semen", "semen@yandex.ru", "asdfsadf", EnumSet.of(Role.ROLE_USER));
        ResultActions actions = mockMvc.perform(post(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(expected)))
                .andDo(print())
                .andExpect(status().isCreated());


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
