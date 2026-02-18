package com.micka.banque.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/hello")
    public String hello(){
        return "Hello Spring boot !";
    }

    @GetMapping("/bonjour")
    public String bonjour(@RequestParam String nom) {
        return "Bonjour " + nom + " ! Bienvenue sur Spring Boot ! 🎉";
    }
}
