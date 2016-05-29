package ru.dvvar.graduate.model;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.util.Date;
import java.util.EnumSet;
import java.util.Set;

/**
 * Created by Dmitriy_Varygin on 03.04.2016.
 */
@NamedQueries({
        @NamedQuery(name = User.DELETE, query = "DELETE FROM User u WHERE u.id=:id"),
        @NamedQuery(name = User.GET_ALL, query = "SELECT DISTINCT u FROM User u ORDER BY u.name DESC"),
        @NamedQuery(name = User.GET_BY_EMAIL, query = "SELECT u FROM User u WHERE u.email=:email")
})
@Entity
@Table(name = "users", uniqueConstraints = {@UniqueConstraint(columnNames = "email", name = "users_unique_email_idx")})
public class User extends NamedEntity {

    public static final String DELETE = "User.delete";
    public static final String GET_ALL = "User.getAll";
    public static final String GET_BY_EMAIL = "User.getByEmail";

    @Column(name = "menu_upvote_id", nullable = false)
    private int menuUpvoteId;

    @Column(name = "email", nullable = false, unique = true)
    @Email
    @NotEmpty
    private String email;

    @Column(name = "password", nullable = false)
    @NotEmpty
    @Length(min = 5)
    private String password;

    @Column(name = "registered", columnDefinition = "timestamp default now()")
    private Date registered = new Date();

    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "role")
    @ElementCollection(fetch = FetchType.EAGER)
    private Set<Role> roles;

    public User() {

    }

    public User(User that) {
        super(that.getId(), that.getName());
        this.menuUpvoteId = that.menuUpvoteId;
        this.email = that.email;
        this.password = that.password;
        this.registered = that.registered;
        this.roles = EnumSet.copyOf(that.getRoles());

    }

    public User(Integer id, String name, String email, String password, Set<Role> roles) {
        super(id, name);
        this.email = email;
        this.password = password;
        this.roles = roles;
    }

    public User(String name, String email, String password, Set<Role> roles) {
        super(name);
        this.email = email;
        this.password = password;
        this.roles = roles;
    }

    public User(Integer id, String name, String email, String password, Set<Role> roles, int menuUpvoteId) {
        this(id, name, email, password, roles);
        this.menuUpvoteId = menuUpvoteId;
    }

    public int getMenuUpvoteId() {
        return menuUpvoteId;
    }

    public void setMenuUpvoteId(int menuUpvoteId) {
        this.menuUpvoteId = menuUpvoteId;
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

    public Date getRegistered() {
        return registered;
    }

    public void setRegistered(Date registered) {
        this.registered = registered;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("upvoted menu id's", menuUpvoteId)
                .append("name", name)
                .append("email", getEmail())
                .append("password", getPassword())
                .append("registered", registered)
                .append("roles", getRoles())
                .toString();
    }
}
