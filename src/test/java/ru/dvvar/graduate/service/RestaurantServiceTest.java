package ru.dvvar.graduate.service;

import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.dvvar.graduate.model.Restaurant;

import static ru.dvvar.graduate.RestaurantTestData.*;

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
    RestaurantService service;

    @Test
    public void shouldGet() throws Exception {
        Restaurant restaurant = service.get(RESTAURANT_ID_1);
        assertTrue(restaurant != null);
    }
}
