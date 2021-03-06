package ru.dvvar.graduate.util.matcher;

import org.junit.Assert;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultMatcher;
import ru.dvvar.graduate.util.json.JsonUtil;

import java.io.UnsupportedEncodingException;
import java.util.Collection;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

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

    public void assertListsEquals(List<T> expected, List<T> actual) {
        Assert.assertTrue(expected.equals(actual));
    }

    public ResultMatcher isContentMatch(T expect) {
        return content().string(
                new TestMatcher<T>(expect) {
                    @Override
                    protected boolean compare(T expected, String actual) {
                        R actualForCompare = entityConverter.apply(fromJsonValue(actual));
                        R expectedForCompare = entityConverter.apply(expected);
                        return expectedForCompare.equals(actualForCompare);
                    }
                }
        );
    }

    public ResultMatcher isContentListMatch(List<T> expected) {
        return content().string(new TestMatcher<List<T>>(expected) {
            @Override
            protected boolean compare(List<T> expected, String actual) {
                List<R> actualList = map(fromJsonValues(actual), entityConverter);
                List<R> expectedList = map(expected, entityConverter);
                return expectedList.equals(actualList);
            }
        });
    }

    public T fromJsonAction(ResultActions actions) throws UnsupportedEncodingException {
        return fromJsonValue(actions.andReturn().getResponse().getContentAsString());
    }

    private T fromJsonValue(String json) {
        return JsonUtil.readValue(json, entityClass);
    }

    private <S, T> List<T> map(Collection<S> collection, Function<S, T> converter) {
        return collection
                .stream()
                .map(converter)
                .collect(Collectors.toList());
    }

    private Collection<T> fromJsonValues(String json) {
        return JsonUtil.readValues(json, entityClass);
    }

}
