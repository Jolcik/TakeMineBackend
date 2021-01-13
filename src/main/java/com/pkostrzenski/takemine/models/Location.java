package com.pkostrzenski.takemine.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "locations")
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private int id;

    @NotNull
    private String name;

    @NotNull
    private double lat;

    @NotNull
    private double lng;

    @NotNull
    private boolean monday;

    @NotNull
    private boolean tuesday;

    @NotNull
    private boolean wednesday;

    @NotNull
    private boolean thursday;

    @NotNull
    private boolean friday;

    @NotNull
    private boolean saturday;

    @NotNull
    private boolean sunday;

    @ManyToOne
    @JoinColumn(name="product_id")
    @JsonIgnore
    private Product product;

    @ManyToOne
    @JoinColumn(name="notifier_id")
    @JsonIgnore
    private Notifier notifier;

    @NotNull
    private String fromHour;

    @NotNull
    private String toHour;


    public Location() { }

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

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public boolean isMonday() {
        return monday;
    }

    public void setMonday(boolean monday) {
        this.monday = monday;
    }

    public boolean isTuesday() {
        return tuesday;
    }

    public void setTuesday(boolean tuesday) {
        this.tuesday = tuesday;
    }

    public boolean isWednesday() {
        return wednesday;
    }

    public void setWednesday(boolean wednesday) {
        this.wednesday = wednesday;
    }

    public boolean isThursday() {
        return thursday;
    }

    public void setThursday(boolean thursday) {
        this.thursday = thursday;
    }

    public boolean isFriday() {
        return friday;
    }

    public void setFriday(boolean friday) {
        this.friday = friday;
    }

    public boolean isSaturday() {
        return saturday;
    }

    public void setSaturday(boolean saturday) {
        this.saturday = saturday;
    }

    public boolean isSunday() {
        return sunday;
    }

    public void setSunday(boolean sunday) {
        this.sunday = sunday;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public String getFromHour() {
        return fromHour;
    }

    public void setFromHour(String fromHour) {
        this.fromHour = fromHour;
    }

    public String getToHour() {
        return toHour;
    }

    public void setToHour(String toHour) {
        this.toHour = toHour;
    }

    public Notifier getNotifier() {
        return notifier;
    }

    public void setNotifier(Notifier notifier) {
        this.notifier = notifier;
    }

    public boolean daysOverlap(Location location) {
        return this.isMonday() == location.isMonday() ||
                this.isTuesday() == location.isTuesday() ||
                this.isWednesday() == location.isWednesday() ||
                this.isThursday() == location.isThursday() ||
                this.isFriday() == location.isFriday() ||
                this.isSaturday() == location.isSaturday() ||
                this.isSunday() == location.isSunday();
    }

    public boolean hoursOverlap(Location location) {
        int from1 = minutesFromHour(this.getFromHour());
        int from2 = minutesFromHour(location.getFromHour());
        int to1 = minutesFromHour(this.getToHour());
        int to2 = minutesFromHour(location.getToHour());

        return from2 <= to1 && from1 <= to2;
    }

    private int minutesFromHour(String hour) {
        // hour in format "18:21"
        return Integer.parseInt(hour.substring(0, 2)) * 60 + Integer.parseInt(hour.substring(3, 5));
    }

    /*
     * Returns distance between two points in meters
     */
    public double calculateDistance(Location location) {
        double theta = this.getLng() - location.getLng();
        double dist = Math.sin(Math.toRadians(this.getLat())) * Math.sin(Math.toRadians(location.getLat())) + Math.cos(Math.toRadians(this.getLat())) * Math.cos(Math.toRadians(location.getLat())) * Math.cos(Math.toRadians(theta));
        dist = Math.acos(dist);
        dist = Math.toDegrees(dist);
        dist = dist * 60 * 1.1515;

        return dist * 1.609344 * 1000;
    }
}
