package com.pkostrzenski.takemine.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;


@Entity
@Table(name = "city")
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, nullable = false)
    private int id;

    @NotNull
    private String name;
    private double centerLat;
    private double centerLng;
    private double centerZoom;

    public City() {}

    public City(int id, @NotNull String name, double centerLat, double centerLng) {
        this.id = id;
        this.name = name;
        this.centerLat = centerLat;
        this.centerLng = centerLng;
    }

    public City(String name, double centerLat, double centerLng){
        this.name = name;
        this.centerLat = centerLat;
        this.centerLng = centerLng;
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

    public double getCenterLat() {
        return centerLat;
    }

    public void setCenterLat(double centerLat) {
        this.centerLat = centerLat;
    }

    public double getCenterLng() {
        return centerLng;
    }

    public void setCenterLng(double centerLng) {
        this.centerLng = centerLng;
    }

    public double getCenterZoom() {
        return centerZoom;
    }

    public void setCenterZoom(double centerZoom) {
        this.centerZoom = centerZoom;
    }
}
