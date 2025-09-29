package com.example.cdapizza.service;

import com.example.cdapizza.model.Pizza;
import com.example.cdapizza.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

/**
 * Service d'initialisation des données de base
 */
@Service
public class InitializationService implements CommandLineRunner {
    
    @Autowired
    private PizzaRepository pizzaRepository;
    
    @Override
    public void run(String... args) throws Exception {
        initializePizzas();
    }
    
    /**
     * Initialise les pizzas de base si elles n'existent pas
     */
    private void initializePizzas() {
        if (pizzaRepository.count() == 0) {
            List<Pizza> pizzas = Arrays.asList(
                new Pizza("Margherita", "Tomate, mozzarella, basilic frais", new BigDecimal("8.50")),
                new Pizza("Pepperoni", "Tomate, mozzarella, pepperoni", new BigDecimal("10.50")),
                new Pizza("Quatre Fromages", "Tomate, mozzarella, gorgonzola, parmesan, chèvre", new BigDecimal("12.00")),
                new Pizza("Chorizo", "Tomate, mozzarella, chorizo, oignons", new BigDecimal("11.00")),
                new Pizza("Végétarienne", "Tomate, mozzarella, légumes grillés, olives", new BigDecimal("9.50")),
                new Pizza("Hawaïenne", "Tomate, mozzarella, jambon, ananas", new BigDecimal("10.00")),
                new Pizza("Calzone", "Tomate, mozzarella, jambon, champignons (pizza pliée)", new BigDecimal("11.50")),
                new Pizza("Diavola", "Tomate, mozzarella, salami piquant, piments", new BigDecimal("11.00")),
                new Pizza("Prosciutto", "Tomate, mozzarella, jambon de Parme, roquette", new BigDecimal("13.00")),
                new Pizza("Quatre Saisons", "Tomate, mozzarella, jambon, champignons, artichauts, olives", new BigDecimal("12.50"))
            );
            
            pizzaRepository.saveAll(pizzas);
            System.out.println("Pizzas de base initialisées avec succès !");
        }
    }
}
