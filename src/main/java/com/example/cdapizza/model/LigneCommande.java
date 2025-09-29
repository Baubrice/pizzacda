package com.example.cdapizza.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * Entité représentant une ligne de commande (pizza + quantité)
 */
@Entity
@Table(name = "lignes_commande")
public class LigneCommande {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "commande_id", nullable = false)
    private Commande commande;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pizza_id", nullable = false)
    private Pizza pizza;
    
    @NotNull(message = "La quantité est obligatoire")
    @Min(value = 1, message = "La quantité doit être au moins 1")
    @Column(nullable = false)
    private Integer quantite;

    // Constructeurs
    public LigneCommande() {}

    public LigneCommande(Pizza pizza, Integer quantite) {
        this.pizza = pizza;
        this.quantite = quantite;
    }

    // Méthodes utilitaires
    public BigDecimal getSousTotal() {
        if (pizza != null && quantite != null) {
            return pizza.getPrix().multiply(BigDecimal.valueOf(quantite));
        }
        return BigDecimal.ZERO;
    }

    // Getters et Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Commande getCommande() {
        return commande;
    }

    public void setCommande(Commande commande) {
        this.commande = commande;
    }

    public Pizza getPizza() {
        return pizza;
    }

    public void setPizza(Pizza pizza) {
        this.pizza = pizza;
    }

    public Integer getQuantite() {
        return quantite;
    }

    public void setQuantite(Integer quantite) {
        this.quantite = quantite;
    }

    @Override
    public String toString() {
        return "LigneCommande{" +
                "id=" + id +
                ", pizza=" + (pizza != null ? pizza.getNom() : "null") +
                ", quantite=" + quantite +
                '}';
    }
}
