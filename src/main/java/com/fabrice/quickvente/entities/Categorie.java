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
@Table(name = "CATEGORIE")
public class Categorie implements Serializable{
    
    @Id
    @SequenceGenerator(name = "categorieSeq", sequenceName = "CATEGORIE_SEQ", allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "categorieSeq")
    @Column(name = "id_categ", nullable = false)
    private Long id_categ;
    
    @Column(name = "lib_categ", nullable = false, unique = true)
    private String lib_categ;

    public Categorie() {
    }

    public Categorie(String lib_categ) {
        this.lib_categ = lib_categ;
    }

    public Long getId_categ() {
        return id_categ;
    }

    public void setId_categ(Long id_categ) {
        this.id_categ = id_categ;
    }

    public String getLib_categ() {
        return lib_categ;
    }

    public void setLib_categ(String lib_categ) {
        this.lib_categ = lib_categ;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + Objects.hashCode(this.id_categ);
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
        final Categorie other = (Categorie) obj;
        if (!Objects.equals(this.id_categ, other.id_categ)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Categorie{" + "id_categ=" + id_categ + ", lib_categ=" + lib_categ + '}';
    }
    
}
