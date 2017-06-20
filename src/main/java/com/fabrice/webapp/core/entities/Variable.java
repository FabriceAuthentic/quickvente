/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fabrice.webapp.core.entities;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author Fabrice
 */
@Entity
@Table(name = "VARIABLES")
public class Variable extends BaseEntite {

    @Id
    @Column(name = "ID")
    private String id;
    
    @Column(name = "VALEUR")
    private String valeur;
    
    @Column(name = "CATEGORIE")
    private String categorieVariable = null;   

    public Variable() {
    }

    public Variable(String id, String valeur, String categorieVariable) {
        this.id = id;
        this.valeur = valeur;
        this.categorieVariable = categorieVariable;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getValeur() {
        return valeur;
    }

    public int getValeurInt() {
        if(valeur != null){
            try{
                return Integer.parseInt(valeur);
            }catch(NumberFormatException nfe){
                Logger.getLogger(Variable.class.getSimpleName()).log(Level.SEVERE, nfe.getLocalizedMessage(), nfe);
                return 0;
            }
        }
        return 0;
    }

    public void setValeur(String valeur) {
        this.valeur = valeur;
    }

    public String getCategorieVariable() {
        return categorieVariable;
    }

    public void setCategorieVariable(String categorieVariable) {
        this.categorieVariable = categorieVariable;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Variable other = (Variable) obj;
        if ((this.id == null) ? (other.id != null) : !this.id.equals(other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 83 * hash + (this.id != null ? this.id.hashCode() : 0);
        return hash;
    }

    @Override
    public String toString() {
        return "Variable{" + "id=" + id + ", valeur=" + valeur + ", categorieVariable=" + categorieVariable + '}';
    }    
    
    @Override
    public String creerChaineModification(Object o){
        Variable ancien = (Variable) o;
        if(ancien == null) {
            ancien = new Variable();
        }
        String str =  "(";
        if(!this.id.equals(ancien.id)){
            str += "id: " + this.id;
        }
        if(!this.valeur.equals(ancien.valeur)){
            str += ", valeur: " + this.valeur;
        }
        if(!this.categorieVariable.equals(ancien.categorieVariable)){
            str += ", catégorie: " + this.categorieVariable;
        }        
        str += ")";
        setChaineModification(str);
        return str;
    }
}
