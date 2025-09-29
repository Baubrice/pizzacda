package com.example.cdapizza.service;

import com.example.cdapizza.model.Commande;
import com.example.cdapizza.model.EtatCommande;
import com.example.cdapizza.model.LigneCommande;
import com.example.cdapizza.model.Pizza;
import com.example.cdapizza.repository.CommandeRepository;
import com.example.cdapizza.repository.LigneCommandeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

/**
 * Service pour la gestion des commandes
 */
@Service
@Transactional
public class CommandeService {
    
    @Autowired
    private CommandeRepository commandeRepository;
    
    @Autowired
    private LigneCommandeRepository ligneCommandeRepository;
    
    
    /**
     * Récupère toutes les commandes
     */
    public List<Commande> getAllCommandes() {
        return commandeRepository.findAllOrderByDateCommandeDesc();
    }
    
    /**
     * Récupère une commande par son ID
     */
    public Optional<Commande> getCommandeById(Long id) {
        return commandeRepository.findById(id);
    }
    
    /**
     * Récupère toutes les commandes par état
     */
    public List<Commande> getCommandesByEtat(EtatCommande etat) {
        return commandeRepository.findByEtat(etat);
    }
    
    /**
     * Récupère toutes les commandes en cours (en préparation ou prêtes)
     */
    public List<Commande> getCommandesEnCours() {
        return commandeRepository.findCommandesEnCours();
    }
    
    /**
     * Crée une nouvelle commande
     */
    public Commande creerCommande(String nomClient, List<LigneCommande> lignesCommande) {
        Commande commande = new Commande();
        commande.setNomClient(nomClient);
        
        // Calculer le montant total
        BigDecimal montantTotal = BigDecimal.ZERO;
        for (LigneCommande ligne : lignesCommande) {
            ligne.setCommande(commande);
            montantTotal = montantTotal.add(ligne.getSousTotal());
        }
        commande.setMontant(montantTotal);
        
        // Sauvegarder la commande
        Commande commandeSauvegardee = commandeRepository.save(commande);
        
        // Sauvegarder les lignes de commande
        for (LigneCommande ligne : lignesCommande) {
            ligneCommandeRepository.save(ligne);
        }
        
        return commandeSauvegardee;
    }
    
    /**
     * Met à jour l'état d'une commande
     */
    public Commande updateEtatCommande(Long commandeId, EtatCommande nouvelEtat) {
        Commande commande = commandeRepository.findById(commandeId)
                .orElseThrow(() -> new RuntimeException("Commande non trouvée avec l'ID: " + commandeId));
        
        // Validation des transitions d'état
        if (!isTransitionValide(commande.getEtat(), nouvelEtat)) {
            throw new RuntimeException("Transition d'état non autorisée de " + 
                    commande.getEtat() + " vers " + nouvelEtat);
        }
        
        commande.setEtat(nouvelEtat);
        return commandeRepository.save(commande);
    }
    
    /**
     * Valide si une transition d'état est autorisée
     */
    private boolean isTransitionValide(EtatCommande etatActuel, EtatCommande nouvelEtat) {
        switch (etatActuel) {
            case PAYER:
                return nouvelEtat == EtatCommande.PREPARATION;
            case PREPARATION:
                return nouvelEtat == EtatCommande.PRETE;
            case PRETE:
                return nouvelEtat == EtatCommande.LIVRER;
            case LIVRER:
                return false; // Une fois livrée, on ne peut plus changer l'état
            default:
                return false;
        }
    }
    
    /**
     * Supprime une commande
     */
    public void deleteCommande(Long id) {
        // Supprimer d'abord les lignes de commande
        List<LigneCommande> lignes = ligneCommandeRepository.findByCommandeId(id);
        ligneCommandeRepository.deleteAll(lignes);
        
        // Puis supprimer la commande
        commandeRepository.deleteById(id);
    }
}
