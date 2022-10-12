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
    private Set<String> Changes;


}
