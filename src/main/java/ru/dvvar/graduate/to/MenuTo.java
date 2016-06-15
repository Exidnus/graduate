package ru.dvvar.graduate.to;

import ru.dvvar.graduate.model.Dish;

import java.util.List;

/**
 * Created by dmitriy_varygin on 16.06.16.
 */
public class MenuTo {

    private String description;

    private List<Dish> dishes;

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
