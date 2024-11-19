package com.spa.demo.web;


import com.spa.demo.backend.ServicesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {
    @Autowired
    private ServicesRepository servicesRepository;

    @GetMapping("")
    public String showHomePage(){
        return "index";
    }
}
