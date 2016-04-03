package ru.dvvar.graduate.model;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Dmitriy_Varygin on 03.04.2016.
 */
public class Menu extends NamedEntity {

    private List<Dish> dishes;

    public Menu() {

    }

    public Menu(Integer id, String name, List<Dish> dishes) {
        super(id, name);
        this.dishes = dishes;
    }

    public float computePrice() {
        return (float) dishes.stream().collect(Collectors.summarizingDouble(Dish::getPrice)).getSum();
    }

    public List<Dish> getDishes() {
        return dishes;
    }

    public void setDishes(List<Dish> dishes) {
        this.dishes = dishes;
    }
}
