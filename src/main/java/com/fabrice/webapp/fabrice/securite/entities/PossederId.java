/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fabrice.webapp.fabrice.securite.entities;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Embeddable;

/**
 *
 * @author Mikel
 */
@Embeddable
public class PossederId implements Serializable{
    
    private Long profilID;
    private Long droitUtilID;

    public PossederId() {
    }

    public PossederId(Long profilID, Long droitUtilID) {
        this.profilID = profilID;
        this.droitUtilID = droitUtilID;
    }

    public Long getProfilID() {
        return profilID;
    }

    public void setProfilID(Long profilID) {
        this.profilID = profilID;
    }

    public Long getDroitUtilID() {
        return droitUtilID;
    }

    public void setDroitUtilID(Long droitUtilID) {
        this.droitUtilID = droitUtilID;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 41 * hash + Objects.hashCode(this.profilID);
        hash = 41 * hash + Objects.hashCode(this.droitUtilID);
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
        final PossederId other = (PossederId) obj;
        if (!Objects.equals(this.profilID, other.profilID)) {
            return false;
        }
        if (!Objects.equals(this.droitUtilID, other.droitUtilID)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "PossederId{" + "profilID=" + profilID + ", droitUtilID=" + droitUtilID + '}';
    }

    
    
}
