package com.spa.demo.backend;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Cupboard {
    //----------Szekrények------------------
    @Id
    @GeneratedValue
    private int cupboardNumber;
    private int status;     // 1 - Foglalt, 0 - Nem foglalt

    @ManyToOne
    @JoinColumn( name = "identification_personId")
    private Identification identification;
}
