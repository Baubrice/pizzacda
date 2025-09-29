package com.example.cdapizza.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Contrôleur principal pour la page d'accueil
 */
@Controller
public class HomeController {
    
    /**
     * Page d'accueil de l'application
     */
    @GetMapping("/")
    public String home() {
        return "home";
    }
}
