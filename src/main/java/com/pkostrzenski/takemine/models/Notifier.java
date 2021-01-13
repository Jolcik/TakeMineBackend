package com.pkostrzenski.takemine.models;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "notifiers")
public class Notifier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private int id;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    @OneToMany(
            mappedBy="notifier",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL
    )
    private Set<Location> locations;

    @ManyToOne
    @JoinColumn(name="item_id", nullable=false)
    private ItemType itemType;

    public Notifier() { }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<Location> getLocations() {
        return locations;
    }

    public void setLocations(Set<Location> locations) {
        this.locations = locations;
    }

    public ItemType getItemType() {
        return itemType;
    }

    public void setItemType(ItemType itemType) {
        this.itemType = itemType;
    }
}
