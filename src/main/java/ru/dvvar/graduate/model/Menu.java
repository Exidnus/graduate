package ru.dvvar.graduate.model;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Dmitriy_Varygin on 03.04.2016.
 */
@Entity
@Table(name = "menus")
public class Menu extends NamedEntity {

    @Column(name = "description")
    private String description;

    @Column(name = "current_upvotes")
    private int currentUpvotes;

    @Column(name = "all_upvotes")
    private int allUpvotes;

    @OneToMany(cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
    @JoinColumn(name = "menu_id", nullable = false)
    @OrderBy("position")
    private List<Dish> dishes;

    public Menu() {

    }

    public Menu(Integer id, String name, List<Dish> dishes) {
        super(id, name);
        this.dishes = dishes;
    }

    public Menu(Integer id, String name, String description, List<Dish> dishes) {
        this(id, name, dishes);
        this.description = description;
    }

    public Menu(Integer id, String name, String description, List<Dish> dishes, int currentUpvotes) {
        this(id, name, description, dishes);
        this.currentUpvotes = currentUpvotes;
        this.allUpvotes = currentUpvotes;
    }

    public Menu(Integer id, String name, String description, List<Dish> dishes, int currentUpvotes, int allUpvotes) {
        this(id, name, description, dishes, currentUpvotes);
        this.allUpvotes = allUpvotes;
    }

    public void upvote() {
        currentUpvotes++;
    }

    public void cancelUpvote() {
        currentUpvotes--;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
                .appendSuper(super.toString())
                .append("description", description)
                .append("currentUpvotes", currentUpvotes)
                .append("allUpvotes", allUpvotes)
                .append("dishes", dishes)
                .toString();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCurrentUpvotes() {
        return currentUpvotes;
    }

    public void setCurrentUpvotes(int currentUpvotes) {
        this.currentUpvotes = currentUpvotes;
    }

    public int getAllUpvotes() {
        return allUpvotes;
    }

    public void setAllUpvotes(int allUpvotes) {
        this.allUpvotes = allUpvotes;
    }

    public List<Dish> getDishes() {
        return dishes;
    }

    public void setDishes(List<Dish> dishes) {
        this.dishes = dishes;
    }
}
