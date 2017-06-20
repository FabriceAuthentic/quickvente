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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author FABRICE
 */
@Entity
@Table(name = "LIVRAISON")
public class Livraison implements Serializable{
    
    @Id
    @SequenceGenerator(name = "livSeq", sequenceName = "LIV_SEQ", allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "livSeq")
    @Column(name = "id_liv", nullable = false)
    private Long id_liv;
    
    @Column(name = "ref_liv", unique = true, nullable = false)
    private String ref_liv;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "date_liv", nullable = false)
    private Date date_liv;

    public Livraison() {
    }

    public Livraison(String ref_liv, Date date_liv) {
        this.ref_liv = ref_liv;
        this.date_liv = date_liv;
    }

    public Long getId_liv() {
        return id_liv;
    }

    public void setId_liv(Long id_liv) {
        this.id_liv = id_liv;
    }

    public String getRef_liv() {
        return ref_liv;
    }

    public void setRef_liv(String ref_liv) {
        this.ref_liv = ref_liv;
    }

    public Date getDate_liv() {
        return date_liv;
    }

    public void setDate_liv(Date date_liv) {
        this.date_liv = date_liv;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + Objects.hashCode(this.id_liv);
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
        final Livraison other = (Livraison) obj;
        if (!Objects.equals(this.id_liv, other.id_liv)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Livraison{" + "id_liv=" + id_liv + ", ref_liv=" + ref_liv + ", date_liv=" + date_liv + '}';
    }
   
}
