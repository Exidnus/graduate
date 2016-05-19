package ru.dvvar.graduate.model;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.persistence.*;

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

    @Column(name = "position", nullable = false)
    private int position;

    public Dish() {

    }

    public Dish(Integer id, String name, String description, float price, int position) {
        super(id, name);
        this.description = description;
        this.price = price;
        this.position = position;
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

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
                .appendSuper(super.toString())
                .append("description", description)
                .append("price", price)
                .append("position", position)
                .toString();
    }
}
