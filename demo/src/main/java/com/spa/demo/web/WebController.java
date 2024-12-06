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

    @GetMapping("/")
    public String showHomePage(Model model){
      //  List<Registration> registrationList=registrationRepository.findAll();
        List<Identification>  identificationList=identificationRepository.findAll();
      model.addAttribute("identificationList", identificationList);
     //   model.addAttribute("registrationList", registrationList);
     //   System.out.println("Identifications: " + identificationList);
        System.out.println("Betölt");
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

        return "redirect:/";  // A főoldal újra megjeleníti az adatokat
    }

}
