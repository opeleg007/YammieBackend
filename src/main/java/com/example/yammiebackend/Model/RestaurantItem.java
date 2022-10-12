package com.example.yammiebackend.Model;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Set;

@Entity
public class RestaurantItem {
    private @Id @GeneratedValue Long id;
    private Double price;
    @ElementCollection
    private Set<String> changes;

    public RestaurantItem() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Set<String> getChanges() {
        return this.changes;
    }

    public void setChanges(Set<String> changes) {
        this.changes = changes;
    }

    public void addChange(String change) {
        this.changes.add(change);
    }
}
