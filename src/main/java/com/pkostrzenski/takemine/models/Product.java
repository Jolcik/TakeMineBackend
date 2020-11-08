package com.pkostrzenski.takemine.models;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private int id;

    @NotEmpty
    @Column
    private String name;

    @NotNull
    private String description;

    @NotNull
    private String address;

    @NotNull
    private String time;

    @NotNull
    private String date;

    @ManyToOne
    @JoinColumn(name="item_id", nullable=false)
    private ItemType itemType;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User buyer;

    @ManyToOne
    @JoinColumn(name="city_id")
    private City city;

    public Product() { }

    public Product(int id, @NotEmpty String name, @NotNull String description, @NotNull String address, @NotNull String time, @NotNull String date, ItemType itemType, User buyer, City city) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.address = address;
        this.time = time;
        this.date = date;
        this.itemType = itemType;
        this.buyer = buyer;
        this.city = city;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ItemType getItemType() {
        return itemType;
    }

    public void setItemType(ItemType itemType) {
        this.itemType = itemType;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public User getBuyer() {
        return buyer;
    }

    public void setBuyer(User buyer) {
        this.buyer = buyer;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }
}
