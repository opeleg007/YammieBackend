package com.example.yammiebackend.Repository;

import com.example.yammiebackend.Model.PlacedOrder;
import com.example.yammiebackend.Model.Status;
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
            if (!content.equals("")) {
                JsonParser springParser = JsonParserFactory.getJsonParser();
                List<Object> lst = springParser.parseList(content);

                int i = 0;
                lst.forEach(object ->
                {
                    LinkedHashMap<String, Object> map = (LinkedHashMap<String, Object>) object;
                    String name = (String) map.get("name");
                    String email = (String) map.get("email");
                    int id = (int) map.get("id");
                    LocalDateTime timeOfOrder = LocalDateTime.parse((String) map.get("timeOfOrder"));
                    List<String> items = (List<String>) map.get("itemList");
                    Status status = (Status) map.get("status");
                    PlacedOrder orderToSave = new PlacedOrder(id, name, email, timeOfOrder, items, status);
                    log.info("Preloading " + orderRepository.save(orderToSave));
                });
            }
        };
    }
}