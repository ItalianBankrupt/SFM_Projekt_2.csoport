package com.spa.demo.backend;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Restaurant {
    @Id
    @GeneratedValue
    private int id;
    private String item;
    private int price;
    @Enumerated(EnumType.STRING)
    private Type type;

    public enum Type {
        Eloetel,Leves,Foetel,Hal_etel,Teszta,Desszert,Kave,Udito,Sor;
    }
}
