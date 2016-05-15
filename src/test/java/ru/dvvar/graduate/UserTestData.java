package ru.dvvar.graduate;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import ru.dvvar.graduate.model.User;
import ru.dvvar.graduate.util.matcher.ModelMatcher;

import java.util.EnumSet;

import static ru.dvvar.graduate.model.BaseEntity.START_SEQ;
import static ru.dvvar.graduate.model.Role.ROLE_USER;

/**
 * Created by Dmitriy_Varygin on 15.05.2016.
 */
public class UserTestData {

    public static final ModelMatcher<User, TestUser> MATCHER = new ModelMatcher<>(u -> u instanceof TestUser ?
            (TestUser) u : new TestUser(u), User.class);

    public static final int USER_ID = START_SEQ;

    public static final User USER = new User(START_SEQ, "Vasiliy", "vasiliy@yandex.ru", "12345", EnumSet.of(ROLE_USER));

    public static class TestUser extends User {

        public TestUser(User user) {
            super(user.getId(), user.getName(), user.getEmail(), user.getPassword(), user.getRoles());
        }

        @Override
        public boolean equals(Object obj) {

            if (obj == null) return false;
            if (obj == this) return true;
            if (obj.getClass() != getClass()) return false;

            TestUser that = (TestUser) obj;
            return new EqualsBuilder()
                    .append(this.id, that.id)
                    .append(this.name, that.name)
                    .append(this.getEmail(), that.getEmail())
                    .append(this.getPassword(), that.getPassword())
                    .append(this.getRoles(), that.getRoles())
                    .isEquals();
        }

        @Override
        public int hashCode() {
            return new HashCodeBuilder(17, 37)
                    .append(id)
                    .append(name)
                    .append(getEmail())
                    .append(getPassword())
                    .append(getRoles())
                    .toHashCode();
        }

        @Override
        public String toString() {
            return new ToStringBuilder(this)
                    .append("id", id)
                    .append("name", name)
                    .append("email", getEmail())
                    .append("password", getPassword())
                    .append("roles", getRoles())
                    .toString();
        }
    }
}
