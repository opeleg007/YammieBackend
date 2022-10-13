package com.example.yammiebackend.Repository;

import com.example.yammiebackend.Model.PlacedOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<PlacedOrder, Integer> {

}
