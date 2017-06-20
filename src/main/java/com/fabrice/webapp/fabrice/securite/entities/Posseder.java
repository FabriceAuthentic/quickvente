/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fabrice.webapp.fabrice.securite.entities;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author Mikel
 */
@Entity
@Table(name = "POSSEDER")
public class Posseder implements Serializable{
    
    @EmbeddedId
    private PossederId id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "profilID", nullable = false, insertable = false, updatable = false)
    private Profil profil;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "droitUtilID", nullable = false, insertable = false, updatable = false)
    private Droit droitUtilisateur;

    public Posseder() {
    }

    public Posseder(PossederId id, Profil profil, Droit droitUtilisateur) {
        this.id = id;
        this.profil = profil;
        this.droitUtilisateur = droitUtilisateur;
    }

    public PossederId getId() {
        return id;
    }

    public void setId(PossederId id) {
        this.id = id;
    }

    public Profil getProfil() {
        return profil;
    }

    public void setProfil(Profil profil) {
        this.profil = profil;
    }

    public Droit getDroitUtilisateur() {
        return droitUtilisateur;
    }

    public void setDroitUtilisateur(Droit droitUtilisateur) {
        this.droitUtilisateur = droitUtilisateur;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.id);
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
        final Posseder other = (Posseder) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Posseder{" + "id=" + id + ", profil=" + profil + ", droitUtilisateur=" + droitUtilisateur + '}';
    }

    
}
