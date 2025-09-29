package com.example.cdapizza.repository;

import com.example.cdapizza.model.Commande;
import com.example.cdapizza.model.EtatCommande;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Repository pour la gestion des commandes
 */
@Repository
public interface CommandeRepository extends JpaRepository<Commande, Long> {
    
    /**
     * Trouve toutes les commandes par état
     */
    List<Commande> findByEtat(EtatCommande etat);
    
    /**
     * Trouve toutes les commandes par nom de client
     */
    List<Commande> findByNomClientContainingIgnoreCase(String nomClient);
    
    /**
     * Trouve toutes les commandes entre deux dates
     */
    List<Commande> findByDateCommandeBetween(LocalDateTime dateDebut, LocalDateTime dateFin);
    
    /**
     * Trouve toutes les commandes triées par date de commande décroissante
     */
    @Query("SELECT c FROM Commande c ORDER BY c.dateCommande DESC")
    List<Commande> findAllOrderByDateCommandeDesc();
    
    /**
     * Trouve toutes les commandes en préparation ou prêtes
     */
    @Query("SELECT c FROM Commande c WHERE c.etat IN ('PREPARATION', 'PRETE') ORDER BY c.dateCommande ASC")
    List<Commande> findCommandesEnCours();
}
