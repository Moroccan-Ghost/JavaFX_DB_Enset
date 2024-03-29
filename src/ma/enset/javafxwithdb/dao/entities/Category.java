package ma.enset.javafxwithdb.dao.entities;

import java.io.Serializable;

public class Category implements Serializable {
    private long id;
    private String name;

    public Category() {

    }

    public Category(String name) {
        this.name = name;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return  name ;
    }
}
