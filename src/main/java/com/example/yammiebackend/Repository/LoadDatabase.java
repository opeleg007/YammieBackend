package com.example.yammiebackend.Repository;

import com.example.yammiebackend.Model.PlacedOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.json.JsonParser;
import org.springframework.boot.json.JsonParserFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

@Configuration
class LoadDatabase {

    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(OrderRepository orderRepository) {

        return args -> {
            String filePath = "src\\main\\resources\\static\\order-data.json";
            String content = new String(Files.readAllBytes(Paths.get(filePath)));

            JsonParser springParser = JsonParserFactory.getJsonParser();
            List< Object > lst = springParser.parseList(content);

            int i = 0;
            lst.forEach(object ->
            {LinkedHashMap<String, Object> map = (LinkedHashMap<String, Object>)object;
            String firstName = (String) map.get("firstName");
            String lastName = (String) map.get("lastName");
            String email = (String) map.get("email");
            int id = (int) map.get("id");
            LocalDateTime timeOfOrder = LocalDateTime.parse((String) map.get("timeOfOrder"));
            Double price = (Double) map.get("price");
            List<String> items = (List<String>) map.get("itemList");
            PlacedOrder orderToSave = new PlacedOrder(id, firstName, lastName, email, timeOfOrder, price, items);
            log.info("Preloading " + orderRepository.save(orderToSave));});

        };
    }
}