package com.spa.demo.web;



import com.spa.demo.backend.Identification;
import com.spa.demo.backend.IdentificationRepository;
import com.spa.demo.backend.Services;
import com.spa.demo.backend.ServicesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class WebController {
    @Autowired
    private ServicesRepository servicesRepository;
    @Autowired
    private IdentificationRepository identificationRepository;

    @GetMapping("/")
    public String showHomePage(Model model){
        List<Identification> identificationList=identificationRepository.findAll();
        model.addAttribute("identificationList", identificationList);
        System.out.println("Identifications: " + identificationList);
        return "index";

    }

    @GetMapping("/services")
    public String listServices(Model model){
        List<Services> serviceslist = servicesRepository.findAll();
        model.addAttribute("servicesList",serviceslist);
        return "services";
    }
}
