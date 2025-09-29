package com.example.cdapizza.repository;

import com.example.cdapizza.model.LigneCommande;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository pour la gestion des lignes de commande
 */
@Repository
public interface LigneCommandeRepository extends JpaRepository<LigneCommande, Long> {
    
    /**
     * Trouve toutes les lignes de commande pour une commande donnée
     */
    List<LigneCommande> findByCommandeId(Long commandeId);
}
