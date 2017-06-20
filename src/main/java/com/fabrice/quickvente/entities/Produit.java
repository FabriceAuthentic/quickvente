/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fabrice.quickvente.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author FABRICE
 */
@Entity
@Table(name = "PRODUIT")
public class Produit implements Serializable{
    
    @Id
    @SequenceGenerator(name = "produitSeq", sequenceName = "PRODUIT_SEQ", allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "produitSeq")
    @Column(name = "id_prod", nullable = false)
    private Long id_prod;
    
    @Column(name = "lib_prod", nullable = false, unique = true)
    private String lib_prod;
    
    @Column(name = "qte_alerte", nullable = false)
    private Integer qte_alerte;
    
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "date_creation", nullable = false)
    private Date date_creation;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_categ", nullable = true)
    private Categorie categorie;

    public Produit() {
    }

    public Produit(String lib_prod, Integer qte_alerte, Integer qte_stock, Date date_creation, Categorie categorie) {
        this.lib_prod = lib_prod;
        this.qte_alerte = qte_alerte;          
        this.date_creation = date_creation;
        this.categorie = categorie;
    }

    public Long getId_prod() {
        return id_prod;
    }

    public void setId_prod(Long id_prod) {
        this.id_prod = id_prod;
    }

    public String getLib_prod() {
        return lib_prod;
    }

    public void setLib_prod(String lib_prod) {
        this.lib_prod = lib_prod;
    }

    public Integer getQte_alerte() {
        return qte_alerte;
    }

    public void setQte_alerte(Integer qte_alerte) {
        this.qte_alerte = qte_alerte;
    }

    public Date getDate_creation() {
        return date_creation;
    }

    public void setDate_creation(Date date_creation) {
        this.date_creation = date_creation;
    }

    public Categorie getCategorie() {
        return categorie;
    }

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 71 * hash + Objects.hashCode(this.id_prod);
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
        final Produit other = (Produit) obj;
        if (!Objects.equals(this.id_prod, other.id_prod)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Produit{" + "id_prod=" + id_prod + ", lib_prod=" + lib_prod + ", qte_alerte=" + qte_alerte + ", date_creation=" + date_creation + ", categorie=" + categorie + '}';
    }

    
}
