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
@Table(name = "CLIENT")
public class Client implements Serializable{
    
    @Id
    @SequenceGenerator(name = "clientSeq", sequenceName = "CLIENT_SEQ", allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "clientSeq")
    @Column(name = "id_cli", nullable = false)
    private Long id_cli;
    
    @Column(name = "nom_cli", nullable = false, unique = true)
    private String nom_cli;
    
    @Column(name = "tel_cli", nullable = true)
    private String tel_cli;
    
    @Column(name = "adresse_cli", nullable = true)
    private String adresse_cli;

    public Client() {
    }

    public Client(String nom_cli, String tel_cli, String adresse_cli) {
        this.nom_cli = nom_cli;
        this.tel_cli = tel_cli;
        this.adresse_cli = adresse_cli;
    }

    public Long getId_cli() {
        return id_cli;
    }

    public void setId_cli(Long id_cli) {
        this.id_cli = id_cli;
    }

    public String getNom_cli() {
        return nom_cli;
    }

    public void setNom_cli(String nom_cli) {
        this.nom_cli = nom_cli;
    }

    public String getTel_cli() {
        return tel_cli;
    }

    public void setTel_cli(String tel_cli) {
        this.tel_cli = tel_cli;
    }

    public String getAdresse_cli() {
        return adresse_cli;
    }

    public void setAdresse_cli(String adresse_cli) {
        this.adresse_cli = adresse_cli;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 13 * hash + Objects.hashCode(this.id_cli);
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
        final Client other = (Client) obj;
        if (!Objects.equals(this.id_cli, other.id_cli)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Client{" + "id_cli=" + id_cli + ", nom_cli=" + nom_cli + ", tel_cli=" + tel_cli + ", adresse_cli=" + adresse_cli + '}';
    }
    
    
}
