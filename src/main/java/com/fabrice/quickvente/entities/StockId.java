/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fabrice.quickvente.entities;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Embeddable;

/**
 *
 * @author FABRICE
 */
@Embeddable
public class StockId implements Serializable{
    
    private Long id_prod;
    private Long id_liv;

    public StockId() {
    }

    public StockId(Long id_prod, Long id_liv) {
        this.id_prod = id_prod;
        this.id_liv = id_liv;
    }

    public Long getId_prod() {
        return id_prod;
    }

    public void setId_prod(Long id_prod) {
        this.id_prod = id_prod;
    }

    public Long getId_liv() {
        return id_liv;
    }

    public void setId_liv(Long id_liv) {
        this.id_liv = id_liv;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 89 * hash + Objects.hashCode(this.id_prod);
        hash = 89 * hash + Objects.hashCode(this.id_liv);
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
        final StockId other = (StockId) obj;
        if (!Objects.equals(this.id_prod, other.id_prod)) {
            return false;
        }
        if (!Objects.equals(this.id_liv, other.id_liv)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "StockId{" + "id_prod=" + id_prod + ", id_liv=" + id_liv + '}';
    }
    
}
