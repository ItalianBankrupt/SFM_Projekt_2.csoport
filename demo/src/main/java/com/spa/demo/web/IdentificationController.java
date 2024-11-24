package com.spa.demo.web;

import com.spa.demo.backend.Identification;
import com.spa.demo.backend.IdentificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/identification")
public class IdentificationController {

    @Autowired
    private IdentificationRepository identificationRepository;

    @GetMapping("/identification")
    public List<Identification> getIdentification() {
        return identificationRepository.findAll();
    }
}
