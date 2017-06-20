/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fabrice.webapp.core.entities;

import java.io.Serializable;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;
import javax.persistence.Version;

/**
 * Classe de base des objets entité
 *
 * @author fabrice
 */
@MappedSuperclass
public abstract class BaseEntite implements Serializable, Cloneable {

    private static final long serialVersionUID = 1L;
    /**
     * La version de l'objet
     */
    @Version
    private int version = 1;
    /**
     * La chaîne pour récupérer les modifications survenues sur l'objet
     */
    @Transient
    private String chaineModification = "";
        
    /**
     * Id du pointservice connecté pour la génération des Ids
     */
    @Transient
    protected String idPointService;

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public String getChaineModification() {
        return chaineModification;
    }

    protected void setChaineModification(String chaineModification) {
        this.chaineModification = chaineModification;
    }

    public String getIdPointService() {
        return idPointService;
    }

    public void setIdPointService(String idPointService) {
        this.idPointService = idPointService;
    }

    public abstract String creerChaineModification(Object o);
}
