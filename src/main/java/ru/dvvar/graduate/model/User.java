package ru.dvvar.graduate.model;

import java.util.Set;

/**
 * Created by Dmitriy_Varygin on 03.04.2016.
 */
public class User extends NamedEntity {

    private String email;

    private String password;

    private Set<Role> roles;

    public User() {

    }

    public User(Integer id, String name, String email, String password, Set<Role> roles) {
        super(id, name);
        this.email = email;
        this.password = password;
        this.roles = roles;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
