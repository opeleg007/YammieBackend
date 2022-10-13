package com.example.yammiebackend.Repository;

public class OrderNotFoundException extends RuntimeException {

    public OrderNotFoundException(int id) {
        super("Could not find order " + id);
    }
}
