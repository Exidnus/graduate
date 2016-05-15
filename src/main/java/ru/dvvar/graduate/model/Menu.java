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

    @OneToMany(cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
    private List<Dish> dishes;

    public Menu() {

    }

    public Menu(Integer id, String name, List<Dish> dishes) {
        super(id, name);
        this.dishes = dishes;
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

    public List<Dish> getDishes() {
        return dishes;
    }

    public void setDishes(List<Dish> dishes) {
        this.dishes = dishes;
    }
}
