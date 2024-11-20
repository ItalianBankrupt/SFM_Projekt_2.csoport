package com.spa.demo.backend;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Restaurant {
    //----------Ételek, italok és üdítők----
    @Id
    @GeneratedValue
    private long id;
    private String name;
    private int price;
    private String type;
}
