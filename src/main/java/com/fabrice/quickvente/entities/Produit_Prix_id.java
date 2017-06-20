/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fabrice.quickvente.entities;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Embeddable;

/**
 *
 * @author FABRICE
 */
@Embeddable
public class Produit_Prix_id implements Serializable{
    
    private Long id_prod;
    private Long id_prix;

    public Produit_Prix_id() {
    }

    public Long getId_prod() {
        return id_prod;
    }

    public void setId_prod(Long id_prod) {
        this.id_prod = id_prod;
    }

    public Long getId_prix() {
        return id_prix;
    }

    public void setId_prix(Long id_prix) {
        this.id_prix = id_prix;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + Objects.hashCode(this.id_prod);
        hash = 83 * hash + Objects.hashCode(this.id_prix);
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
        final Produit_Prix_id other = (Produit_Prix_id) obj;
        if (!Objects.equals(this.id_prod, other.id_prod)) {
            return false;
        }
        if (!Objects.equals(this.id_prix, other.id_prix)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Produit_Prix_id{" + "id_prod=" + id_prod + ", id_prix=" + id_prix + '}';
    }
    
    
}
