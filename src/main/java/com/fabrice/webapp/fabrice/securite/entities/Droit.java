/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fabrice.webapp.fabrice.securite.entities;

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
 * @author miki
 */
@Entity
@Table(name = "DROIT")
public class Droit implements Serializable{
    
    @Id
    @SequenceGenerator(name = "droitSeq", sequenceName = "DROIT_SEQ", allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "droitSeq")
    @Column(name = "CODE", nullable = false)
    private Long codeDroit;
    
    @Column(name = "LIBELLE", nullable = false)
    private String libDroit;
    
    @Column(name = "CLE", nullable = false)
    private String cleDroit;
    

    public Droit() {
    }

    public Droit(String libDroit, String cleDroit) {
        this.libDroit = libDroit;
        this.cleDroit = cleDroit;
    }
   

    public Long getCodeDroit() {
        return codeDroit;
    }

    public void setCodeDroit(Long codeDroit) {
        this.codeDroit = codeDroit;
    }

    public String getLibDroit() {
        return libDroit;
    }

    public void setLibDroit(String libDroit) {
        this.libDroit = libDroit;
    }


    public String getCleDroit() {
        return cleDroit;
    }

    public void setCleDroit(String cleDroit) {
        this.cleDroit = cleDroit;
    }
    
    

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 89 * hash + Objects.hashCode(this.codeDroit);
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
        final Droit other = (Droit) obj;
        if (!Objects.equals(this.codeDroit, other.codeDroit)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Droit{" + "codeDroit=" + codeDroit + ", libDroit=" + libDroit + ", cleDroit=" + cleDroit + '}';
    }    
    
    
}
