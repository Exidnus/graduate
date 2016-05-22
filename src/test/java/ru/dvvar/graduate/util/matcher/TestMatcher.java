package ru.dvvar.graduate.util.matcher;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import ru.dvvar.graduate.util.json.JsonUtil;

/**
 * Created by Dmitriy_Varygin on 22.05.2016.
 */
public abstract class TestMatcher<T> extends BaseMatcher<String> {

    protected T expected;

    public TestMatcher(T expected) {
        this.expected = expected;
    }

    @Override
    public boolean matches(Object item) {
        return compare(expected, (String) item);
    }

    protected abstract boolean compare(T expected, String actual);

    @Override
    public void describeTo(Description description) {
        description.appendText(JsonUtil.writeValue(expected));
    }
}
