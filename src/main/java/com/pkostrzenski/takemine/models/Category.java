package com.pkostrzenski.takemine.models;

import org.hibernate.annotations.NaturalId;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Entity
@Table(name = "category")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private int id;

    @NotEmpty
    @NaturalId(mutable = true)
    @Column(unique = true)
    private String name;

    @OneToMany(mappedBy="category")
    private List<ItemType> items;

    public Category() { }

    public Category(int id, @NotEmpty String name, List<ItemType> items) {
        this.id = id;
        this.name = name;
        this.items = items;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ItemType> getItems() {
        return items;
    }

    public void setItems(List<ItemType> items) {
        this.items = items;
    }
}
