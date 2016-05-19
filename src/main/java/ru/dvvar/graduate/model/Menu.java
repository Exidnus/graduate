package ru.dvvar.graduate.model;

import javax.persistence.*;
import java.util.List;
import java.util.stream.Collectors;

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

    public float getPrice() {
        return (float) dishes
                .stream()
                .collect(Collectors.summarizingDouble(Dish::getPrice))
                .getSum();
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
