package com.spa.demo.web;



import com.spa.demo.backend.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class WebController {
    @Autowired
    private ServicesRepository servicesRepository;
    @Autowired
    private IdentificationRepository identificationRepository;
    @Autowired
    private RegistrationRepository registrationRepository;
    @Autowired
    private CupboardRepository cupboardRepository;

    @GetMapping("")
    public String showHomePage(Model model){
        System.out.println("showHomePage");
       List<Registration> registrationList=registrationRepository.findAll();
       List<Identification>  identificationList=identificationRepository.findAll();
       List<Cupboard> cupboardList=cupboardRepository.findAll();
       model.addAttribute("registrationList", registrationList)
               .addAttribute("identificationList", identificationList)
               .addAttribute("cupboardList", cupboardList);
        return "index";

    }

    @GetMapping("/services")
    public String listServices(Model model){
        List<Services> serviceslist = servicesRepository.findAll();

        model.addAttribute("servicesList",serviceslist);
        return "services";
    }

    @GetMapping ("/refresh")
    public String addIdentification() {
        System.out.println("Refresh");
        return "redirect:/";  // A főoldal újra megjeleníti az adatokat
    }

}
