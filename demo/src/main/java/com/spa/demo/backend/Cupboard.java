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
    private int cupboardNumber;
    private int status;     // 1 - Foglalt, 0 - Nem foglalt
    @ManyToOne
    @JoinColumn(name = "registration_id")
    private Registration registration;
}
