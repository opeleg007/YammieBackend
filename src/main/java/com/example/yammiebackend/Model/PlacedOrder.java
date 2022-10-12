package com.example.yammiebackend.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class PlacedOrder {
    private @Id @GeneratedValue Long id;
    private String firstName;
    private String lastName;
    private String email;

    private LocalDateTime timeOfOrder;
    private Double price;
    @OneToMany
    private List<RestaurantItem> itemList;

}
