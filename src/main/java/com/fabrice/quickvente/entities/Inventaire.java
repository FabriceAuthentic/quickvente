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
@Table(name = "INVENTAIRE")
public class Inventaire implements Serializable{
    
    @Id
    @SequenceGenerator(name = "inventSeq", sequenceName = "INVENT_SEQ", allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "inventSeq")
    @Column(name = "id_invent", nullable = false)
    private Long id_invent;
    
    @Column(name = "qté_stock_jour", nullable = false)
    private Integer qté_stock_jour;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "date", nullable = false)
    private Date date;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_prod", nullable = true)
    private Produit produit;

    public Inventaire() {
    }

    public Inventaire(Integer qté_stock_jour, Date date, Produit produit) {
        this.qté_stock_jour = qté_stock_jour;
        this.date = date;
        this.produit = produit;
    }

    public Long getId_invent() {
        return id_invent;
    }

    public void setId_invent(Long id_invent) {
        this.id_invent = id_invent;
    }

    public Integer getQté_stock_jour() {
        return qté_stock_jour;
    }

    public void setQté_stock_jour(Integer qté_stock_jour) {
        this.qté_stock_jour = qté_stock_jour;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Produit getProduit() {
        return produit;
    }

    public void setProduit(Produit produit) {
        this.produit = produit;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 71 * hash + Objects.hashCode(this.id_invent);
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
        final Inventaire other = (Inventaire) obj;
        if (!Objects.equals(this.id_invent, other.id_invent)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Inventaire{" + "id_invent=" + id_invent + ", qt\u00e9_stock_jour=" + qté_stock_jour + ", date=" + date + ", produit=" + produit + '}';
    }
    
    
}
