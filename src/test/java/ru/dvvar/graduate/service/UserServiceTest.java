package ru.dvvar.graduate.service;

import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.dvvar.graduate.model.User;

/**
 * Created by Dmitriy_Varygin on 15.05.2016.
 */
@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
public class UserServiceTest extends TestCase {

    @Autowired
    private UserService service;

    @Test
    public void get() throws Exception {
        User user = service.get(100_000);
        assertTrue(user != null);
    }

}