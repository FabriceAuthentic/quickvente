/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fabrice.webapp.fabrice.securite.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author miki
 */
@Entity
@Table(name = "PROFIL")
public class Profil implements Serializable{
    
    @Id
    @SequenceGenerator(name = "profilSeq", sequenceName = "PROFIL_SEQ", allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "profilSeq")
    @Column(name = "ID", nullable = false)
    private Long idProf;
    
    @Column(name = "NOM", nullable = false)
    private String nomProf;
    
    @Column(name = "DESCRIPTION",nullable = true)
    private String description;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DATE_CREATION", nullable = false)
    private Date dateCreaProf;
    
    @OneToMany(mappedBy = "profil",fetch = FetchType.LAZY)
    private Set<Posseder> posseders = new HashSet<Posseder>();
    
    
    @OneToMany(mappedBy = "profil", fetch = FetchType.LAZY)
    private Set<Utilisateur> compteUtilisateurs = new HashSet<Utilisateur>();
    
    

    public Profil() {
    }

    public Profil(String nomProf, String description, Date dateCreaProf) {
        this.nomProf = nomProf;
        this.description = description;
        this.dateCreaProf = dateCreaProf;
    }   
   
    
    public void ajouterPosseder(Posseder p){
        posseders.add(p);
    }
    
    public void supprimerPosseder(Posseder p){
        posseders.remove(p);
    }
    
    public void affecterPosseder(Set<Posseder> ps){
        posseders = ps;
    }

    public Long getIdProf() {
        return idProf;
    }

    public void setIdProf(Long idProf) {
        this.idProf = idProf;
    }

    public String getNomProf() {
        return nomProf;
    }

    public void setNomProf(String nomProf) {
        this.nomProf = nomProf;
    }

    public Date getDateCreaProf() {
        return dateCreaProf;
    }

    public void setDateCreaProf(Date dateCreaProf) {
        this.dateCreaProf = dateCreaProf;
    }

    public Set<Posseder> getPosseders() {
        return posseders;
    }

    public void setPosseders(Set<Posseder> posseders) {
        this.posseders = posseders;
    }

    public Set<Utilisateur> getCompteUtilisateurs() {
        return compteUtilisateurs;
    }

    public void setCompteUtilisateurs(Set<Utilisateur> compteUtilisateurs) {
        this.compteUtilisateurs = compteUtilisateurs;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    
    

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + Objects.hashCode(this.idProf);
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
        final Profil other = (Profil) obj;
        if (!Objects.equals(this.idProf, other.idProf)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Profil{" + "idProf=" + idProf + ", nomProf=" + nomProf + ", description=" + description + ", dateCreaProf=" + dateCreaProf + ", posseders=" + posseders + ", compteUtilisateurs=" + compteUtilisateurs + '}';
    }

    

}
   
