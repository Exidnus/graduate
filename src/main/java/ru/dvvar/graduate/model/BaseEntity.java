package ru.dvvar.graduate.model;

/**
 * Created by Dmitriy_Varygin on 03.04.2016.
 */
public class BaseEntity {

    protected Integer id;

    public BaseEntity() {

    }

    protected BaseEntity(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public boolean isNew() {
        return id == null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BaseEntity that = (BaseEntity) o;

        if (id == null || that.id == null) return false;
        return id.equals(that.id);

    }

    @Override
    public int hashCode() {
        return id == null ? 0 : id;
    }
}
