package com.example.yammiebackend.Repository;

import com.example.yammiebackend.Model.PlacedOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class LoadDatabase {

    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(OrderRepository orderRepository) {

        return args -> {
            log.info("Preloading " + orderRepository.save(new
                    PlacedOrder("Or", "Peleg", "opeleg007@gmail.com")));
            log.info("Preloading " + orderRepository.save(new
                    PlacedOrder("Mai", "Elfassi", "elfassimai@gmail.com")));
        };
    }
}