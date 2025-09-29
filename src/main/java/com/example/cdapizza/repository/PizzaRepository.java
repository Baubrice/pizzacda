package com.example.cdapizza.repository;

import com.example.cdapizza.model.Pizza;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository pour la gestion des pizzas
 */
@Repository
public interface PizzaRepository extends JpaRepository<Pizza, Long> {
    
    /**
     * Trouve toutes les pizzas disponibles
     */
    List<Pizza> findByDisponibleTrue();
    
    /**
     * Trouve une pizza par son nom
     */
    Pizza findByNom(String nom);
}
