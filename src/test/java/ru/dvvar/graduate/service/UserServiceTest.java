package ru.dvvar.graduate.service;

import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.dvvar.graduate.model.Role;
import ru.dvvar.graduate.model.User;
import ru.dvvar.graduate.repository.UserRepository;

import java.util.EnumSet;

import static ru.dvvar.graduate.UserTestData.*;

/**
 * Created by Dmitriy_Varygin on 15.05.2016.
 */
@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
public class UserServiceTest extends TestCase {

    @Autowired
    private UserService service;

    @Autowired
    private UserRepository repository;

    @Test
    public void shouldGet() throws Exception {
        MATCHER.assertEquals(USUAL_USER, service.get(USER_ID));
    }

    @Test
    public void shouldDelete() throws Exception {
        assertTrue(service.delete(USER_ID));
    }

    @Test
    public void shouldNotDelete() throws Exception {
        assertFalse(service.delete(1));
    }

    @Test
    public void shouldUpdate() throws Exception {
        TestUser updated = new TestUser(USUAL_USER);
        updated.setName("James");
        updated.setEmail("james@google.com");
        updated.setRoles(EnumSet.allOf(Role.class));
        service.update(updated.asUser());
        MATCHER.assertEquals(updated, service.get(updated.getId()));
    }

    @Test
    public void shouldSave() throws Exception {
        User saved = service.save(new User("James", "james@google.com", "james12345", EnumSet.of(Role.ROLE_ADMIN)));
        MATCHER.assertEquals(saved, service.get(saved.getId()));
    }

    @Test
    public void shouldGetAll() throws Exception {
        MATCHER.assertListsEquals(USERS_ORDERED_BY_NAME, service.getAll());
    }

    @Test
    public void shouldGetByEmail() throws Exception {
        MATCHER.assertEquals(USUAL_USER, repository.getByEmail(USUAL_USER.getEmail()));
    }

}