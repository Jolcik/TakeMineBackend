package com.pkostrzenski.takemine.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private int id;

    @NotEmpty(message = "Username can not be empty")
    @NaturalId(mutable = true)
    @Column(unique = true)
    private String username;

    @NotNull
    @Column(unique = true)
    private String email;

    @OneToMany(
            mappedBy="user",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL
    )
    private Set<Location> locations;

    @JsonIgnore
    private String password;
    @JsonIgnore
    private boolean active;
    @JsonIgnore
    private String roles;

    public User() {}
    public User(int id, String username, String email, String password, boolean active, String roles) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.active = active;
        this.roles = roles;
    }

    public User(String username, String email, String password){
        this.username = username;
        this.email = email;
        this.password = password;
        this.active = true;
        this.roles = "USER";
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public void update(User updatedUser){
        if(updatedUser.getUsername() != null)
            this.username = updatedUser.getUsername();

        if(updatedUser.getEmail() != null)
            this.email = updatedUser.getEmail();
    }
}
