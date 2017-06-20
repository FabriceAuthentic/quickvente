/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fabrice.quickvente.entities;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 *
 * @author FABRICE
 */
@Entity
@Table(name = "PRIX")
public class Prix implements Serializable{
    
    @Id
    @SequenceGenerator(name = "prixSeq", sequenceName = "PRIX_SEQ", allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "prixSeq")
    @Column(name = "id_prix", nullable = false)
    private Long id_prix;
    
    @Column(name = "lib_prix", nullable = false)
    private String lib_prix;

    public Prix() {
    }

    public Prix(String lib_prix) {
        this.lib_prix = lib_prix;
    }

    public Long getId_prix() {
        return id_prix;
    }

    public void setId_prix(Long id_prix) {
        this.id_prix = id_prix;
    }

    public String getLib_prix() {
        return lib_prix;
    }

    public void setLib_prix(String lib_prix) {
        this.lib_prix = lib_prix;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 37 * hash + Objects.hashCode(this.id_prix);
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
        final Prix other = (Prix) obj;
        if (!Objects.equals(this.id_prix, other.id_prix)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Prix{" + "id_prix=" + id_prix + ", lib_prix=" + lib_prix + '}';
    }
   
  
}
