package com.example.cdapizza.service;

import com.example.cdapizza.model.Pizza;
import com.example.cdapizza.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service pour la gestion des pizzas
 */
@Service
@Transactional
public class PizzaService {
    
    @Autowired
    private PizzaRepository pizzaRepository;
    
    /**
     * Récupère toutes les pizzas
     */
    public List<Pizza> getAllPizzas() {
        return pizzaRepository.findAll();
    }
    
    /**
     * Récupère toutes les pizzas disponibles
     */
    public List<Pizza> getPizzasDisponibles() {
        return pizzaRepository.findByDisponibleTrue();
    }
    
    /**
     * Récupère une pizza par son ID
     */
    public Optional<Pizza> getPizzaById(Long id) {
        return pizzaRepository.findById(id);
    }
    
    /**
     * Sauvegarde une pizza
     */
    public Pizza savePizza(Pizza pizza) {
        return pizzaRepository.save(pizza);
    }
    
    /**
     * Supprime une pizza
     */
    public void deletePizza(Long id) {
        pizzaRepository.deleteById(id);
    }
    
    /**
     * Met à jour le statut de disponibilité d'une pizza
     */
    public Pizza toggleDisponibilite(Long id) {
        Pizza pizza = pizzaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pizza non trouvée avec l'ID: " + id));
        pizza.setDisponible(!pizza.isDisponible());
        return pizzaRepository.save(pizza);
    }
}
