package com.example.cdapizza.controller;

import com.example.cdapizza.model.Commande;
import com.example.cdapizza.model.EtatCommande;
import com.example.cdapizza.model.LigneCommande;
import com.example.cdapizza.model.Pizza;
import com.example.cdapizza.service.CommandeService;
import com.example.cdapizza.service.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

/**
 * Contrôleur pour l'interface caisse
 */
@Controller
@RequestMapping("/caisse")
public class CaisseController {
    
    @Autowired
    private PizzaService pizzaService;
    
    @Autowired
    private CommandeService commandeService;
    
    /**
     * Affiche la page principale de la caisse
     */
    @GetMapping
    public String index(Model model) {
        model.addAttribute("pizzas", pizzaService.getPizzasDisponibles());
        model.addAttribute("commandes", commandeService.getAllCommandes());
        return "caisse/index";
    }
    
    /**
     * Affiche le formulaire de création de commande
     */
    @GetMapping("/nouvelle-commande")
    public String nouvelleCommande(Model model) {
        model.addAttribute("pizzas", pizzaService.getPizzasDisponibles());
        model.addAttribute("lignesCommande", new ArrayList<LigneCommande>());
        return "caisse/nouvelle-commande";
    }
    
    /**
     * Traite la création d'une nouvelle commande
     */
    @PostMapping("/creer-commande")
    public String creerCommande(@RequestParam String nomClient,
                               @RequestParam List<Long> pizzaIds,
                               @RequestParam List<Integer> quantites,
                               RedirectAttributes redirectAttributes) {
        try {
            List<LigneCommande> lignesCommande = new ArrayList<>();
            
            for (int i = 0; i < pizzaIds.size(); i++) {
                Long pizzaId = pizzaIds.get(i);
                Integer quantite = quantites.get(i);
                
                if (quantite != null && quantite > 0) {
                    Pizza pizza = pizzaService.getPizzaById(pizzaId)
                            .orElseThrow(() -> new RuntimeException("Pizza non trouvée"));
                    
                    LigneCommande ligne = new LigneCommande(pizza, quantite);
                    lignesCommande.add(ligne);
                }
            }
            
            if (lignesCommande.isEmpty()) {
                redirectAttributes.addFlashAttribute("error", "Veuillez sélectionner au moins une pizza");
                return "redirect:/caisse/nouvelle-commande";
            }
            
            Commande commande = commandeService.creerCommande(nomClient, lignesCommande);
            redirectAttributes.addFlashAttribute("success", "Commande créée avec succès ! ID: " + commande.getId());
            
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Erreur lors de la création de la commande: " + e.getMessage());
        }
        
        return "redirect:/caisse";
    }
    
    /**
     * Met à jour l'état d'une commande (PAYER -> LIVRER)
     */
    @PostMapping("/{id}/changer-etat")
    public String changerEtatCommande(@PathVariable Long id, 
                                    @RequestParam EtatCommande nouvelEtat,
                                    RedirectAttributes redirectAttributes) {
        try {
            Commande commande = commandeService.getCommandeById(id)
                    .orElseThrow(() -> new RuntimeException("Commande non trouvée"));
            
            // Seules les transitions PAYER -> LIVRER sont autorisées depuis la caisse
            if (commande.getEtat() == EtatCommande.PAYER && nouvelEtat == EtatCommande.LIVRER) {
                commandeService.updateEtatCommande(id, nouvelEtat);
                redirectAttributes.addFlashAttribute("success", "Commande marquée comme livrée");
            } else {
                redirectAttributes.addFlashAttribute("error", "Transition d'état non autorisée");
            }
            
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Erreur: " + e.getMessage());
        }
        
        return "redirect:/caisse";
    }
    
    /**
     * Affiche les détails d'une commande
     */
    @GetMapping("/commande/{id}")
    public String detailsCommande(@PathVariable Long id, Model model) {
        Commande commande = commandeService.getCommandeById(id)
                .orElseThrow(() -> new RuntimeException("Commande non trouvée"));
        
        model.addAttribute("commande", commande);
        return "caisse/details-commande";
    }
}
