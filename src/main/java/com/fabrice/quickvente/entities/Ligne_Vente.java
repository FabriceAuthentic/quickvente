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
@Table(name = "Ligne_Vente")
public class Ligne_Vente implements Serializable{
    
    @EmbeddedId
    private Ligne_Vente_Id id;
    
    @Column(name = "qte_vente", nullable = false)
    private Integer qte_vente;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_prod", nullable = false, insertable = false, updatable = false)
    private Produit produit;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_vente", nullable = false, insertable = false, updatable = false)
    private Vente vente;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_cli", nullable = false, insertable = false, updatable = false)
    private Client client;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_prix", nullable = false, insertable = false, updatable = false)
    private Prix prix;
    
    public Ligne_Vente() {
    }

    public Ligne_Vente(Ligne_Vente_Id id, Integer qte_vente, Produit produit, Vente vente, Client client, Prix prix) {
        this.id = id;
        this.qte_vente = qte_vente;
        this.produit = produit;
        this.vente = vente;
        this.client = client;
        this.prix = prix;
    }
        
    public Ligne_Vente_Id getId() {
        return id;
    }

    public void setId(Ligne_Vente_Id id) {
        this.id = id;
    }

    public Integer getQte_vente() {
        return qte_vente;
    }

    public void setQte_vente(Integer qte_vente) {
        this.qte_vente = qte_vente;
    }

    public Produit getProduit() {
        return produit;
    }

    public void setProduit(Produit produit) {
        this.produit = produit;
    }

    public Vente getVente() {
        return vente;
    }

    public void setVente(Vente vente) {
        this.vente = vente;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Prix getPrix() {
        return prix;
    }

    public void setPrix(Prix prix) {
        this.prix = prix;
    }
        
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.id);
        hash = 89 * hash + Objects.hashCode(this.produit);
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
        final Ligne_Vente other = (Ligne_Vente) obj;
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
        return "Ligne_Vente{" + "id=" + id + ", qte_vente=" + qte_vente + ", produit=" + produit + ", vente=" + vente + ", client=" + client + ", prix=" + prix + '}';
    }

        
}
