package com.spa.demo.backend;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Services {
    @Id
    @GeneratedValue
    private int id;
    private String item;
    private int price;
    @Enumerated(EnumType.STRING)
    private Type type;

    public enum Type {
        Belepo, Szolgaltatas;
    }
}
