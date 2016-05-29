package ru.dvvar.graduate.model;

import org.springframework.security.core.GrantedAuthority;

/**
 * Created by Dmitriy_Varygin on 03.04.2016.
 */
public enum Role implements GrantedAuthority {
    ROLE_USER,
    ROLE_ADMIN;


    @Override
    public String getAuthority() {
        return name();
    }
}
