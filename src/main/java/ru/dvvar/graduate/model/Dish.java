package ru.dvvar.graduate.model;

/**
 * Created by Dmitriy_Varygin on 03.04.2016.
 */
public class Dish extends NamedEntity {

    private float price;

    public Dish() {

    }

    public Dish(Integer id, String name, float price) {
        super(id, name);
        this.price = price;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}
