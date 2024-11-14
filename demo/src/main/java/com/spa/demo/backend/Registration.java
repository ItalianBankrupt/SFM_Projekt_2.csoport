package com.spa.demo.backend;


import jakarta.persistence.Entity;
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
public class Registration {
    @Id
    private String Id;
    private String Name;
    private String City;
    private String Street;
    private String PostCode;
}
