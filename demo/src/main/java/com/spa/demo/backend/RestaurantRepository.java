package com.spa.demo.backend;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
    List<Restaurant> findByName(String name);
    List<Restaurant> findByType(String type);   // Eloetel | Leves | Foetel | Hal_etel | Teszta | Desszert | Kave | Udito | Sor
}

