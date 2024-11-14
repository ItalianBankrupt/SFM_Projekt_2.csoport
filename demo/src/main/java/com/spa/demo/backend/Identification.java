package com.spa.demo.backend;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
public class Identification {
    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne
    @JoinColumn(name =" registration_id")
    private Registration registration;

    @ManyToOne
    @JoinColumn(name = "services_id")
    private Services services;

    private int darabszam;
    private int ticketType;
}
