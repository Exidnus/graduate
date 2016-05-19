package ru.dvvar.graduate.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by Dmitriy_Varygin on 03.04.2016.
 */
@Entity
@Table(name = "dishes")
public class Dish extends NamedEntity {

    @Column(name = "description")
    private String description;

    @Column(name = "price", nullable = false)
    private float price;

    public Dish() {

    }

    public Dish(Integer id, String name, String description, float price) {
        super(id, name);
        this.description = description;
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}
