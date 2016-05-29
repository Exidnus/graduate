package ru.dvvar.graduate.web;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import ru.dvvar.graduate.model.User;

import java.util.Objects;

/**
 * Created by Dmitriy_Varygin on 15.05.2016.
 */
public class LoggedUser extends org.springframework.security.core.userdetails.User {

    private User user;

    public LoggedUser(User user) {
        super(user.getEmail(), user.getPassword(), true, true, true, true, user.getRoles());
        this.user = user;
    }

    public static LoggedUser safeGet() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) return null;
        Object user = authentication.getPrincipal();
        return (user instanceof LoggedUser) ? (LoggedUser) user : null;
    }

    public static LoggedUser get() {
        final LoggedUser user = safeGet();
        Objects.requireNonNull(user, "No authorized user found");
        return user;
    }

    public static int getId() {
        return get().user.getId();
        //return 100_000;
    }


}
