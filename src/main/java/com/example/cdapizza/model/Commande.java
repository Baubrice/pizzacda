package com.example.cdapizza.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Entité représentant une commande
 */
@Entity
@Table(name = "commandes")
public class Commande {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank(message = "Le nom du client est obligatoire")
    @Column(nullable = false)
    private String nomClient;
    
    @NotNull(message = "La date de commande est obligatoire")
    @Column(nullable = false)
    private LocalDateTime dateCommande;
    
    @NotNull(message = "Le montant est obligatoire")
    @DecimalMin(value = "0.0", inclusive = false, message = "Le montant doit être positif")
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal montant;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EtatCommande etat = EtatCommande.PAYER;
    
    @OneToMany(mappedBy = "commande", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<LigneCommande> lignesCommande = new ArrayList<>();

    // Constructeurs
    public Commande() {
        this.dateCommande = LocalDateTime.now();
    }

    public Commande(String nomClient, BigDecimal montant) {
        this();
        this.nomClient = nomClient;
        this.montant = montant;
    }

    // Méthodes utilitaires
    public void ajouterLigneCommande(LigneCommande ligneCommande) {
        lignesCommande.add(ligneCommande);
        ligneCommande.setCommande(this);
    }

    public void retirerLigneCommande(LigneCommande ligneCommande) {
        lignesCommande.remove(ligneCommande);
        ligneCommande.setCommande(null);
    }

    // Getters et Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomClient() {
        return nomClient;
    }

    public void setNomClient(String nomClient) {
        this.nomClient = nomClient;
    }

    public LocalDateTime getDateCommande() {
        return dateCommande;
    }

    public void setDateCommande(LocalDateTime dateCommande) {
        this.dateCommande = dateCommande;
    }

    public BigDecimal getMontant() {
        return montant;
    }

    public void setMontant(BigDecimal montant) {
        this.montant = montant;
    }

    public EtatCommande getEtat() {
        return etat;
    }

    public void setEtat(EtatCommande etat) {
        this.etat = etat;
    }

    public List<LigneCommande> getLignesCommande() {
        return lignesCommande;
    }

    public void setLignesCommande(List<LigneCommande> lignesCommande) {
        this.lignesCommande = lignesCommande;
    }

    @Override
    public String toString() {
        return "Commande{" +
                "id=" + id +
                ", nomClient='" + nomClient + '\'' +
                ", dateCommande=" + dateCommande +
                ", montant=" + montant +
                ", etat=" + etat +
                '}';
    }
}
