package com.pkostrzenski.takemine.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
@Table(name = "products")
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

    @ManyToOne
    @JoinColumn(name="item_id", nullable=false)
    private ItemType itemType;

    @ManyToOne
    @JoinColumn(name="owner_id")
    @JsonIgnore
    private User owner;

    @ManyToOne
    @JoinColumn(name="buyer_id")
    @JsonIgnore
    private User buyer;

    @ManyToOne
    @JoinColumn(name="city_id")
    private City city;

    @OneToMany(
            mappedBy="product",
            fetch = FetchType.LAZY,
            cascade = CascadeType.MERGE
    )
    private Set<Picture> pictures;

    @OneToMany(
            mappedBy="product",
            fetch = FetchType.LAZY,
            cascade = CascadeType.MERGE
    )
    private Set<Location> locations;

    @NotNull
    private boolean sold = false;

    public Product() { }

    public Product(int id, @NotEmpty String name, @NotNull String description, ItemType itemType, User buyer, City city) {
        this.id = id;
        this.name = name;
        this.description = description;
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

    public Set<Picture> getPictures() {
        return pictures;
    }

    public void setPictures(Set<Picture> pictures) {
        this.pictures = pictures;
    }

    public Set<Location> getLocations() {
        return locations;
    }

    public void setLocations(Set<Location> locations) {
        this.locations = locations;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public boolean isSold() {
        return sold;
    }

    public void setSold(boolean sold) {
        this.sold = sold;
    }
}
