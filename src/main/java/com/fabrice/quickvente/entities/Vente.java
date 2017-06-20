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
import org.apache.poi.ss.usermodel.Name;

/**
 *
 * @author FABRICE
 */
@Entity
@Table(name = "VENTE")
public class Vente implements Serializable{
    
    @Id
    @SequenceGenerator(name = "venteSeq", sequenceName = "VENTE_SEQ", allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "venteSeq")
    @Column(name = "id_vente", nullable = false)
    private Long id_vente;
    
    @Column(name = "etat_vente", nullable = false)
    private Boolean etat_vente;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "date_vente", nullable = false)
    private Date date_vente;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_caiss", nullable = true)
    private Caissier caissier;
    
    public Vente() {
    }

    public Vente(Boolean etat_vente, Date date_vente, Caissier caissier) {
        this.etat_vente = etat_vente;
        this.date_vente = date_vente;
        this.caissier = caissier;
    }

    public Long getId_vente() {
        return id_vente;
    }

    public void setId_vente(Long id_vente) {
        this.id_vente = id_vente;
    }

    public Boolean getEtat_vente() {
        return etat_vente;
    }

    public void setEtat_vente(Boolean etat_vente) {
        this.etat_vente = etat_vente;
    }

    public Caissier getCaissier() {
        return caissier;
    }

    public void setCaissier(Caissier caissier) {
        this.caissier = caissier;
    }

    public Date getDate_vente() {
        return date_vente;
    }

    public void setDate_vente(Date date_vente) {
        this.date_vente = date_vente;
    }
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + Objects.hashCode(this.id_vente);
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
        final Vente other = (Vente) obj;
        if (!Objects.equals(this.id_vente, other.id_vente)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Vente{" + "id_vente=" + id_vente + ", etat_vente=" + etat_vente + ", date_vente=" + date_vente + ", caissier=" + caissier + '}';
    }
            
}