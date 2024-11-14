package com.spa.demo.backend;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Cupboard {
    @Id
    @GeneratedValue
    private Long id;
    private int cupboardNumber;

    @ManyToOne
    @JoinColumn(name = "registration_id")
    private Registration registration;
}
