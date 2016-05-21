package ru.dvvar.graduate.model;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Dmitriy_Varygin on 03.04.2016.
 */
@Entity
@Table(name = "restaurants", uniqueConstraints = @UniqueConstraint(columnNames = "name", name = "restaurants_unique_name_idx"))
@NamedQueries({
        @NamedQuery(name = Restaurant.DELETE, query = "DELETE FROM Restaurant r WHERE r.id=:id"),
        @NamedQuery(name = Restaurant.GET_ALL, query = "SELECT r FROM Restaurant r ORDER BY r.name"),
        @NamedQuery(name = Restaurant.GET_ONE_WITH_ALL_MENUS, query = "SELECT DISTINCT r FROM Restaurant r " +
                "LEFT JOIN FETCH r.menus WHERE r.id=:id")
})
public class Restaurant extends NamedEntity {

    public static final String DELETE = "Restaurant.delete";
    public static final String GET_ALL = "Restaurant.getAll";
    public static final String GET_ONE_WITH_ALL_MENUS = "Restaurant.getOneWithAllMenus";

    @Column(name = "description")
    private String description;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "current_menu_id")
    private Menu currentMenu;

    @OneToMany(cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id", nullable = false)
    @OrderBy("allUpvotes DESC")
    private List<Menu> menus;

    public Restaurant() {

    }

    public Restaurant(Integer id, String name, String description) {
        super(id, name);
        this.description = description;
    }

    public Restaurant(Integer id, String name, String description, Menu currentMenu) {
        this(id, name, description);
        this.currentMenu = currentMenu;
    }

    public Restaurant(String name, String description, Menu currentMenu) {
        super(name);
        this.description = description;
        this.currentMenu = currentMenu;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
                .appendSuper(super.toString())
                .append("description", description)
                .append("currentMenu", currentMenu)
                .toString();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Menu getCurrentMenu() {
        return currentMenu;
    }

    public void setCurrentMenu(Menu currentMenu) {
        this.currentMenu = currentMenu;
    }

    public List<Menu> getMenus() {
        return menus;
    }

    public void setMenus(List<Menu> menus) {
        this.menus = menus;
    }
}
