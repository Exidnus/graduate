package ru.dvvar.graduate.util.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectReader;

import java.io.IOException;
import java.util.List;

/**
 * Created by Dmitriy_Varygin on 22.05.2016.
 */
public class JsonUtil {

    private JsonUtil() {

    }

    public static <T> List<T> readValues(String json, Class<T> clazz) {
        final ObjectReader reader = JacksonObjectMapper.getInstance().readerFor(clazz);
        try {
            return reader.<T>readValues(json).readAll();
        } catch (IOException e) {
            throw new IllegalArgumentException("Invalid read array from JSON:\n'" + json + "'", e);
        }
    }

    public static <T> T readValue(String json, Class<T> clazz) {
        try {
            return JacksonObjectMapper.getInstance().readValue(json, clazz);
        } catch (IOException e) {
            throw new IllegalArgumentException("Invalid read array from JSON:\n'" + json + "'", e);
        }
    }

    public static <T> String writeValue(T object) {
        try {
            return JacksonObjectMapper.getInstance().writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException("Invalid read array from JSON:\n'" + object + "'", e);
        }
    }
}
