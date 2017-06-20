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
@Table(name = "STOCK")
public class Stock implements Serializable{
    
    @EmbeddedId
    private StockId id;
    
    @Column(name = "qte_stock", nullable = false)
    private Integer qte_stock;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_prod", nullable = false, insertable = false, updatable = false)
    private Produit produit;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_liv", nullable = false, insertable = false, updatable = false)
    private Livraison livraison;

    public Stock() {
    }

    public Stock(StockId id, Integer qte_stock, Produit produit, Livraison livraison) {
        this.id = id;
        this.qte_stock = qte_stock;
        this.produit = produit;
        this.livraison = livraison;
    }

    public StockId getId() {
        return id;
    }

    public void setId(StockId id) {
        this.id = id;
    }

    public Integer getQte_stock() {
        return qte_stock;
    }

    public void setQte_stock(Integer qte_stock) {
        this.qte_stock = qte_stock;
    }

    public Produit getProduit() {
        return produit;
    }

    public void setProduit(Produit produit) {
        this.produit = produit;
    }

    public Livraison getLivraison() {
        return livraison;
    }

    public void setLivraison(Livraison livraison) {
        this.livraison = livraison;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 59 * hash + Objects.hashCode(this.id);
        hash = 59 * hash + Objects.hashCode(this.produit);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Stock other = (Stock) obj;
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
        return "Stock{" + "id=" + id + ", qte_stock=" + qte_stock + ", produit=" + produit + ", livraison=" + livraison + '}';
    }

}
