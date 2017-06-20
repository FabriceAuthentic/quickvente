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
@Table(name = "POSTE")
public class Poste implements Serializable{
    
    @Id
    @SequenceGenerator(name = "posteSeq", sequenceName = "POSTE_SEQ", allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "posteSeq")
    @Column(name = "ID", nullable = false)
    private Long idLibPoste;
       
    @Column(name = "LIBELLE", nullable = false, unique = true)
    private String libPoste;
        

    public Poste() {
        
    }

    public Poste(String libPoste) {
        
        this.libPoste = libPoste;
    }

    public Long getIdLibPoste() {
        return idLibPoste;
    }

    public void setIdLibPoste(Long idLibPoste) {
        this.idLibPoste = idLibPoste;
    }   

    public String getLibPoste() {
        return libPoste;
    }

    public void setLibPoste(String libPoste) {
        this.libPoste = libPoste;
    }

    @Override
    public int hashCode() {
        int hash = 5;
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
        final Poste other = (Poste) obj;
        if (!Objects.equals(this.idLibPoste, other.idLibPoste)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Poste{" + "idLibPoste=" + idLibPoste + ", libPoste=" + libPoste + '}';
    }

    
   
    }

