package com.example.yammiebackend.Model;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Entity
public class PlacedOrder {
    static int idAssigner = 1;
    private @Id int id;
    private String name;
    private String email;

    private LocalDateTime timeOfOrder;
    @ElementCollection
    private List<String> itemList;
    private Status status;

    public PlacedOrder() {
        timeOfOrder = LocalDateTime.now();
        this.id = idAssigner++;
        status = Status.IN_PROGRESS;
    }

    public PlacedOrder(String name, String email) {
        this.name = name;
        this.email = email;
        timeOfOrder = LocalDateTime.now();
        this.id = idAssigner++;
        status = Status.IN_PROGRESS;
    }

    public PlacedOrder(int id, String name, String email, LocalDateTime timeOfOrder, List<String> itemList, Status status) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.timeOfOrder = timeOfOrder;
        this.itemList = itemList;
        this.status = status;
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

    public List<String> getItemList() {
        return itemList;
    }

    public void setItemList(List<String> itemList) {
        this.itemList = itemList;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
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
        return Objects.hash(this.id, this.name, this.email, this.timeOfOrder);
    }

    private String outputItemListAsString() {
        String output = "[";
        for (int i = 0; i < itemList.size(); i++) {
            output += "\"" + itemList.get(i) + "\"";
            if (i < itemList.size() - 1)
                output += ", ";
        }
        output += "]";
        return output;
    }

    @Override
    public String toString() {
        return "{" + "\"id\":" + this.id + ", \"name\":\"" + this.name +
                '\"' + ", \"email\":\"" + this.email +
                '\"' + ", \"timeOfOrder\":\"" + this.timeOfOrder +
                '\"' + ", \"itemList\":" + outputItemListAsString() + '}';
    }
}
