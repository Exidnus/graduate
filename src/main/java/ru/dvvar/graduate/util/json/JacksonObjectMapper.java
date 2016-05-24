package ru.dvvar.graduate.util.json;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

/**
 * Created by Dmitriy_Varygin on 22.05.2016.
 */
public class JacksonObjectMapper extends ObjectMapper {

    private static final ObjectMapper INSTANCE = new JacksonObjectMapper();

    private JacksonObjectMapper() {
        /*registerModule(new Hibernate5Module());
        registerModule(new JavaTimeModule());*/

        configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.NONE);
        setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
        setSerializationInclusion(JsonInclude.Include.NON_NULL);
    }

    public static ObjectMapper getInstance() {
        return INSTANCE;
    }
}
