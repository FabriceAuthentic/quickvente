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
@Table(name = "CAISSIER")
public class Caissier implements Serializable{
    
    @Id
    @SequenceGenerator(name = "caissierSeq", sequenceName = "CAISSIER_SEQ", allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "caissierSeq")
    @Column(name = "id_caiss", nullable = false)
    private Long id_caiss;
    
    @Column(name = "nom_caiss", nullable = false, unique = true)
    private String nom_caiss;
    
    @Column(name = "tel_caiss", nullable = false, unique = true)
    private String tel_caiss;

    public Caissier() {
    }

    public Caissier(String nom_caiss, String tel_caiss) {
        this.nom_caiss = nom_caiss;
        this.tel_caiss = tel_caiss;
    }

    public Long getId_caiss() {
        return id_caiss;
    }

    public void setId_caiss(Long id_caiss) {
        this.id_caiss = id_caiss;
    }

    public String getNom_caiss() {
        return nom_caiss;
    }

    public void setNom_caiss(String nom_caiss) {
        this.nom_caiss = nom_caiss;
    }

    public String getTel_caiss() {
        return tel_caiss;
    }

    public void setTel_caiss(String tel_caiss) {
        this.tel_caiss = tel_caiss;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.id_caiss);
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
        final Caissier other = (Caissier) obj;
        if (!Objects.equals(this.id_caiss, other.id_caiss)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Caissier{" + "id_caiss=" + id_caiss + ", nom_caiss=" + nom_caiss + ", tel_caiss=" + tel_caiss + '}';
    }
    
    
}
