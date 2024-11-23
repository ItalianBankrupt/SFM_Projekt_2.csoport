package com.spa.demo.backend;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class RestaurantRepositoryTest {

    @Autowired
    private RestaurantRepository restaurantRepository;

    void testFindByName() {
        Restaurant restaurant = Restaurant.builder()
                .name("Alma leves")
                .price(4000)
                .type("Leves")
                .id(2)
                .build();

        restaurantRepository.save(restaurant);
        List<Restaurant> restaurants = restaurantRepository.findByName("Alma leves");
        assertEquals(restaurants.size(), 1);
        assertEquals(restaurants.get(0).getName(), restaurant.getName());
        assertEquals(restaurants.get(0).getPrice(), restaurant.getPrice());
        assertEquals(restaurants.get(0).getType(), restaurant.getType());
        assertEquals(restaurants.get(0).getId(), restaurant.getId());

    }
}
