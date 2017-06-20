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
public class Ligne_Vente_Id implements Serializable{
    
    private Long id_prod;
    private Long id_vente;
    private Long id_cli;
    private Long id_prix;

    public Ligne_Vente_Id() {
    }

    public Ligne_Vente_Id(Long id_prod, Long id_vente, Long id_cli, Long id_prix) {
        this.id_prod = id_prod;
        this.id_vente = id_vente;
        this.id_cli = id_cli;
        this.id_prix = id_prix;
    }
    
    public Long getId_prod() {
        return id_prod;
    }

    public void setId_prod(Long id_prod) {
        this.id_prod = id_prod;
    }

    public Long getId_vente() {
        return id_vente;
    }

    public void setId_vente(Long id_vente) {
        this.id_vente = id_vente;
    }

    public Long getId_cli() {
        return id_cli;
    }

    public void setId_cli(Long id_cli) {
        this.id_cli = id_cli;
    }

    public Long getId_prix() {
        return id_prix;
    }

    public void setId_prix(Long id_prix) {
        this.id_prix = id_prix;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 13 * hash + Objects.hashCode(this.id_prod);
        hash = 13 * hash + Objects.hashCode(this.id_vente);
        hash = 13 * hash + Objects.hashCode(this.id_cli);
        hash = 13 * hash + Objects.hashCode(this.id_prix);
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
        final Ligne_Vente_Id other = (Ligne_Vente_Id) obj;
        if (!Objects.equals(this.id_prod, other.id_prod)) {
            return false;
        }
        if (!Objects.equals(this.id_vente, other.id_vente)) {
            return false;
        }
        if (!Objects.equals(this.id_cli, other.id_cli)) {
            return false;
        }
        if (!Objects.equals(this.id_prix, other.id_prix)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Ligne_Vente_Id{" + "id_prod=" + id_prod + ", id_vente=" + id_vente + ", id_cli=" + id_cli + ", id_prix=" + id_prix + '}';
    }
      
         
}
