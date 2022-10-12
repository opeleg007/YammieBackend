package com.example.yammiebackend.Controller;

import com.example.yammiebackend.Model.PlacedOrder;
import com.example.yammiebackend.Repository.OrderNotFoundException;
import com.example.yammiebackend.Repository.OrderRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @PutMapping("/orders")
    public PlacedOrder newOrder(@RequestBody PlacedOrder placedOrder) {
        return orderRepository.save(placedOrder);
    }

    @GetMapping("/orders/{id}")
    public PlacedOrder getSingleOrder(@PathVariable Long id) {
        return orderRepository.findById(id).orElseThrow(() -> new OrderNotFoundException(id));
    }

    @PutMapping("/orders/{id}")
    public PlacedOrder changeOrder(@RequestBody PlacedOrder newPlacedOrder, @PathVariable Long id) {

        return orderRepository.findById(id)
                .map(placedOrder -> {
                    placedOrder.setFirstName(newPlacedOrder.getFirstName());
                    placedOrder.setLastName(newPlacedOrder.getLastName());
                    placedOrder.setEmail(newPlacedOrder.getEmail());
                    placedOrder.setItemList(newPlacedOrder.getItemList());
                    placedOrder.setTimeOfOrder(newPlacedOrder.getTimeOfOrder());
                    placedOrder.setId(newPlacedOrder.getId());
                    return orderRepository.save(placedOrder);
                })
                .orElseGet(() -> {
                    newPlacedOrder.setId(id);
                    return orderRepository.save(newPlacedOrder);
                });
    }

    @DeleteMapping("/orders/{id}")
    void deleteOrder(@PathVariable Long id) {
        orderRepository.deleteById(id);
    }
}
