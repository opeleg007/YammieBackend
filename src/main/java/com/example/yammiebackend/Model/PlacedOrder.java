package com.example.yammiebackend.Model;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.time.LocalDateTime;
import java.util.*;

@Entity
public class PlacedOrder {
    static int idAssigner = 1;
    private @Id int id;
    private String firstName;
    private String lastName;
    private String email;

    private LocalDateTime timeOfOrder;
    private Double price;
    @ElementCollection
    private List<String> itemList;

    public PlacedOrder() {
        timeOfOrder = LocalDateTime.now();
        this.id = idAssigner++;
    }

    public PlacedOrder(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        timeOfOrder = LocalDateTime.now();
        this.id = idAssigner++;
    }

    public PlacedOrder(int id, String firstName, String lastName, String email, LocalDateTime timeOfOrder, Double price, List<String> itemList) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.timeOfOrder = timeOfOrder;
        this.price = price;
        this.itemList = itemList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDateTime getTimeOfOrder() {
        return timeOfOrder;
    }

    public void setTimeOfOrder(LocalDateTime timeOfOrder) {
        this.timeOfOrder = timeOfOrder;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public List<String> getItemList() {
        return itemList;
    }

    public void setItemList(List<String> itemList) {
        this.itemList = itemList;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o)
            return true;
        if (!(o instanceof PlacedOrder))
            return false;
        PlacedOrder placedOrder = (PlacedOrder) o;
        return Objects.equals(this.id, placedOrder.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.firstName, this.lastName, this.email, this.timeOfOrder);
    }

    @Override
    public String toString() {
        return "Order{" + "id=" + this.id + ", first name='" + this.firstName +
                '\'' + ", last name='" + this.lastName + '\'' + ", email='" + this.email +
                '\'' + ", time of order='" + this.timeOfOrder +
                '\'' + ", price='" + this.price + '\'' + '}';
    }
}
