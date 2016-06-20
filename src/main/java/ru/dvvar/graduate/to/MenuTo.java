package ru.dvvar.graduate.to;

import ru.dvvar.graduate.model.Dish;

import java.util.List;

/**
 * Created by dmitriy_varygin on 16.06.16.
 */
public class MenuTo {

    private Integer id;

    private String description;

    private List<Dish> dishes;

    public MenuTo() {

    }

    public MenuTo(Integer id, String description, List<Dish> dishes) {
        this.id = id;
        this.description = description;
        this.dishes = dishes;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Dish> getDishes() {
        return dishes;
    }

    public void setDishes(List<Dish> dishes) {
        this.dishes = dishes;
    }
}
