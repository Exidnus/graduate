package ru.dvvar.graduate.model;

/**
 * Created by Dmitriy_Varygin on 03.04.2016.
 */
public class NamedEntity extends BaseEntity {

    protected String name;

    public NamedEntity() {

    }

    protected NamedEntity(Integer id, String name) {
        super(id);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
