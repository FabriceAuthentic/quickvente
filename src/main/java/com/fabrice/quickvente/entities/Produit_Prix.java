/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fabrice.quickvente.entities;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author FABRICE
 */
@Entity
@Table(name = "Produit_Prix")
public class Produit_Prix implements Serializable{
    
    @EmbeddedId
    private Produit_Prix_id id;
    
    @Column(name = "prix_unit", nullable = false)
    private Integer prix_unit;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_prod", nullable = false, insertable = false, updatable = false)
    private Produit produit;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_prix", nullable = false, insertable = false, updatable = false)
    private Prix prix;

    public Produit_Prix() {
    }

    public Produit_Prix(Produit_Prix_id id, Integer prix_unit, Produit produit, Prix prix) {
        this.id = id;
        this.prix_unit = prix_unit;
        this.produit = produit;
        this.prix = prix;
    }

    public Produit_Prix_id getId() {
        return id;
    }

    public void setId(Produit_Prix_id id) {
        this.id = id;
    }

    public Integer getPrix_unit() {
        return prix_unit;
    }

    public void setPrix_unit(Integer prix_unit) {
        this.prix_unit = prix_unit;
    }

    public Produit getProduit() {
        return produit;
    }

    public void setProduit(Produit produit) {
        this.produit = produit;
    }

    public Prix getPrix() {
        return prix;
    }

    public void setPrix(Prix prix) {
        this.prix = prix;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 29 * hash + Objects.hashCode(this.id);
        hash = 29 * hash + Objects.hashCode(this.produit);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Produit_Prix other = (Produit_Prix) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.produit, other.produit)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Produit_Prix{" + "id=" + id + ", prix_unit=" + prix_unit + ", produit=" + produit + ", prix=" + prix + '}';
    }
    
    
}
