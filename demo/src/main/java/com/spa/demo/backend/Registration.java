package com.spa.demo.backend;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Registration {
    @Id
    private String GeneratedId;
    private String IDNumber;
    private String name;
    private String City;
    private String Street;
    private String PostCode;
    private int CostumerType; // 0 - Mindenkinek, 1 - Felnőtt, 2 - Diák, 3 - Nyugdíjjas

    @OneToMany(mappedBy = "registration", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Identification> identifications;
}
