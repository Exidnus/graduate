package ru.dvvar.graduate.util.matcher;

import org.junit.Assert;

import java.util.function.Function;

/**
 * Created by Dmitriy_Varygin on 15.05.2016.
 */
public class ModelMatcher<T, R> {

    private Function<T, R> entityConverter;
    private Class<T> entityClass;

    public ModelMatcher(Function<T, R> entityConverter, Class<T> entityClass) {
        this.entityConverter = entityConverter;
        this.entityClass = entityClass;
    }

    public void assertEquals(T expected, T actual) {
        Assert.assertEquals(entityConverter.apply(expected), entityConverter.apply(actual));
    }
}
