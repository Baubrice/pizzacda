package com.example.cdapizza.model;

/**
 * Énumération représentant les différents états d'une commande
 */
public enum EtatCommande {
    PAYER("Payée"),
    PREPARATION("En préparation"),
    PRETE("Prête"),
    LIVRER("Livrée");

    private final String libelle;

    EtatCommande(String libelle) {
        this.libelle = libelle;
    }

    public String getLibelle() {
        return libelle;
    }
}
