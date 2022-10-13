package com.example.yammiebackend.Controller;

import com.example.yammiebackend.Model.PlacedOrder;
import com.example.yammiebackend.Repository.OrderNotFoundException;
import com.example.yammiebackend.Repository.OrderRepository;
import com.google.gson.Gson;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class OrderController {
    private final OrderRepository orderRepository;

    OrderController(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @GetMapping("/orders")
    public List<PlacedOrder> getAllOrders() {
        return orderRepository.findAll();
    }

    @GetMapping("/orders-by-period/{interval}")
    public List<PlacedOrder> getPeriodicalOrders(@PathVariable String interval) {
        List<PlacedOrder> allOrders = orderRepository.findAll();
        List<PlacedOrder> toReturn = null;
        int period = 0;
        if (interval.equals("Day"))
            period = 1;
        if (interval.equals("Week"))
            period = 7;
        if (interval.equals("Month"))
            period = 30;
        int finalPeriod = period;
        toReturn = allOrders.stream().filter(customer ->
                (LocalDateTime.now().isAfter(customer.getTimeOfOrder().minusDays(finalPeriod)))).collect(Collectors.toList());

        return toReturn;
    }

    @PostMapping("/orders")
    public PlacedOrder newOrder(@RequestBody PlacedOrder placedOrder) throws IOException {
        PlacedOrder toReturn = orderRepository.save(placedOrder);
        String filePath = "src\\main\\resources\\static\\order-data.json";
        BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
        writer.write(orderRepository.findAll().toString());
        writer.close();
        return toReturn;
    }

    @GetMapping("/orders/{id}")
    public PlacedOrder getSingleOrder(@PathVariable int id) {
        return orderRepository.findById(id).orElseThrow(() -> new OrderNotFoundException(id));
    }

    @PutMapping("/orders/{id}")
    public PlacedOrder changeOrder(@RequestBody PlacedOrder newPlacedOrder, @PathVariable int id) {

        return orderRepository.findById(id)
                .map(placedOrder -> {
                    placedOrder.setName(newPlacedOrder.getName());
                    placedOrder.setEmail(newPlacedOrder.getEmail());
                    placedOrder.setItemList(newPlacedOrder.getItemList());
                    PlacedOrder toReturn = orderRepository.save(placedOrder);
                    String filePath = "src\\main\\resources\\static\\order-data.json";
                    BufferedWriter writer = null;
                    try {
                        writer = new BufferedWriter(new FileWriter(filePath));
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    try {
                        writer.write(orderRepository.findAll().toString());
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    try {
                        writer.close();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    return toReturn;
                })
                .orElseGet(() -> {
                    newPlacedOrder.setId(id);
                    return orderRepository.save(newPlacedOrder);
                });
    }
}
