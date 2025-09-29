package com.example.cdapizza.controller;

import com.example.cdapizza.model.Commande;
import com.example.cdapizza.model.EtatCommande;
import com.example.cdapizza.service.CommandeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

/**
 * Contrôleur pour l'interface cuisine
 */
@Controller
@RequestMapping("/cuisine")
public class CuisineController {
    
    @Autowired
    private CommandeService commandeService;
    
    /**
     * Affiche la page principale de la cuisine
     */
    @GetMapping
    public String index(Model model) {
        List<Commande> commandesEnCours = commandeService.getCommandesEnCours();
        List<Commande> commandesPreparation = commandeService.getCommandesByEtat(EtatCommande.PREPARATION);
        List<Commande> commandesPrete = commandeService.getCommandesByEtat(EtatCommande.PRETE);
        
        model.addAttribute("commandesEnCours", commandesEnCours);
        model.addAttribute("commandesPreparation", commandesPreparation);
        model.addAttribute("commandesPrete", commandesPrete);
        
        return "cuisine/index";
    }
    
    /**
     * Met à jour l'état d'une commande (PREPARATION -> PRETE)
     */
    @PostMapping("/{id}/changer-etat")
    public String changerEtatCommande(@PathVariable Long id, 
                                    @RequestParam EtatCommande nouvelEtat,
                                    RedirectAttributes redirectAttributes) {
        try {
            Commande commande = commandeService.getCommandeById(id)
                    .orElseThrow(() -> new RuntimeException("Commande non trouvée"));
            
            // Seules les transitions PREPARATION -> PRETE sont autorisées depuis la cuisine
            if (commande.getEtat() == EtatCommande.PREPARATION && nouvelEtat == EtatCommande.PRETE) {
                commandeService.updateEtatCommande(id, nouvelEtat);
                redirectAttributes.addFlashAttribute("success", "Commande marquée comme prête");
            } else {
                redirectAttributes.addFlashAttribute("error", "Transition d'état non autorisée");
            }
            
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Erreur: " + e.getMessage());
        }
        
        return "redirect:/cuisine";
    }
    
    /**
     * Affiche les détails d'une commande
     */
    @GetMapping("/commande/{id}")
    public String detailsCommande(@PathVariable Long id, Model model) {
        Commande commande = commandeService.getCommandeById(id)
                .orElseThrow(() -> new RuntimeException("Commande non trouvée"));
        
        model.addAttribute("commande", commande);
        return "cuisine/details-commande";
    }
    
    /**
     * Met à jour l'état d'une commande via AJAX
     */
    @PostMapping("/{id}/update-etat")
    @ResponseBody
    public String updateEtatCommandeAjax(@PathVariable Long id, 
                                       @RequestParam EtatCommande nouvelEtat) {
        try {
            Commande commande = commandeService.getCommandeById(id)
                    .orElseThrow(() -> new RuntimeException("Commande non trouvée"));
            
            if (commande.getEtat() == EtatCommande.PREPARATION && nouvelEtat == EtatCommande.PRETE) {
                commandeService.updateEtatCommande(id, nouvelEtat);
                return "success";
            } else {
                return "error: Transition non autorisée";
            }
            
        } catch (Exception e) {
            return "error: " + e.getMessage();
        }
    }
}
