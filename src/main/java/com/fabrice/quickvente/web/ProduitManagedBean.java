/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fabrice.quickvente.web;

import com.fabrice.quickvente.entities.Categorie;
import com.fabrice.quickvente.entities.Produit;
import com.fabrice.quickvente.services.CategorieSessionBeanLocal;
import com.fabrice.quickvente.services.ProduitSessionBeanLocal;
import com.fabrice.quickvente.utils.constante;
import com.fabrice.webapp.core.Transaction.TransactionManager;
import com.fabrice.webapp.core.Utils.Mtm;
import com.fabrice.webapp.shiro.EntityRealm;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

/**
 *
 * @author FABRICE
 */
@ManagedBean
@ViewScoped
public class ProduitManagedBean implements Serializable{

    private Produit produit;
    private Categorie categorie;
    private Mtm mtm;
    
    private List<Produit> produitListe;
    private List<Categorie> categorieListe;
    
    @EJB
    ProduitSessionBeanLocal produitServices;
    
    @EJB
    CategorieSessionBeanLocal categorieServices;
    
    public ProduitManagedBean() {
        
        produit = new Produit();
        categorie = new Categorie();
        mtm = new Mtm();
        
        produitListe = new ArrayList<>();
        categorieListe = new ArrayList<>();
      
    }
    
    public void gestionCategorie() {
       
        UserTransaction tx = TransactionManager.getUserTransaction();
        try {
            tx.begin();
            if(categorie.getLib_categ().trim().isEmpty()) {
                mtm.mikiMessageWarn("Veuillez saisir le nom de la catégorie svp !");
            } else {
                if (categorie.getId_categ() == null) {
                    categorieServices.saveOne(categorie);
                    tx.commit();
                    categorie = new Categorie();
                    mtm.mikiMessageInfo();
                } else {
                    categorieServices.updateOne(categorie);
                    tx.commit();
                    categorie = new Categorie();
                    mtm.mikiMessageInfo();
                }
            }
        } catch (Exception e) {
            try {
                tx.rollback();
            } catch (IllegalStateException ex1) {
                Logger.getLogger(ProduitManagedBean.class.getName()).log(Level.SEVERE, null, ex1);
            } catch (SecurityException ex1) {
                Logger.getLogger(ProduitManagedBean.class.getName()).log(Level.SEVERE, null, ex1);
            } catch (SystemException ex1) {
                Logger.getLogger(ProduitManagedBean.class.getName()).log(Level.SEVERE, null, ex1);
            }
            mtm.mikiMessageError();
        }
    }
    
    public void renvoiCategorie(Categorie categ) {
        categorie = categ;
    }
    
    public void annulerCategorie() {
        categorie = new Categorie();
    }
    
    public void gestionProduit() {        
        if(EntityRealm.getSubject().isPermitted(constante.ROLE_CREER_PRODUIT_CLE) || EntityRealm.getSubject().isPermitted(constante.ROLE_MODIFIER_PRODUIT_CLE)) {
            UserTransaction tx = TransactionManager.getUserTransaction();
            try {
                tx.begin();
                if(produit.getLib_prod().trim().isEmpty()) {
                    mtm.mikiMessageWarn("Veuillez saisir le nom du produit svp !");
                } else if((produit.getQte_alerte() == 0) || produit.getQte_alerte().toString().trim().isEmpty()) {
                    mtm.mikiMessageWarn("Veuillez saisir la quantité alerte du produit svp !");
                } else if(produit.getCategorie() == null) {
                    mtm.mikiMessageWarn("Veuillez selectionner la catégorie du produit svp !");
                } else {
                    if(produit.getId_prod() == null) {
                        produit.setDate_creation(new Date());
                        produitServices.saveOne(produit);
                        tx.commit();
                        mtm.logMikiLog4j(ProduitManagedBean.class.getName(), org.apache.log4j.Level.INFO, "Enregistrement du produit: " + produit.getLib_prod());
                        produit = new Produit();
                        mtm.mikiMessageInfo();
                    } else {
                        produitServices.updateOne(produit);
                        mtm.logMikiLog4j(ProduitManagedBean.class.getName(), org.apache.log4j.Level.INFO, "Modification du produit: " + produit.getLib_prod());
                        tx.commit();
                        produit = new Produit();
                        mtm.mikiMessageInfo();
                    }
                }
            } catch (Exception e) {
                try {
                    tx.rollback();
                } catch (IllegalStateException ex1) {
                    Logger.getLogger(ProduitManagedBean.class.getName()).log(Level.SEVERE, null, ex1);
                } catch (SecurityException ex1) {
                    Logger.getLogger(ProduitManagedBean.class.getName()).log(Level.SEVERE, null, ex1);
                } catch (SystemException ex1) {
                    Logger.getLogger(ProduitManagedBean.class.getName()).log(Level.SEVERE, null, ex1);
                }
                mtm.mikiMessageError();                
            }
        } else {
            mtm.mikiLog4jMessageError();
        }
    }
    
    public void renvoieProduit(Produit prod) {
        if(EntityRealm.getSubject().isPermitted(constante.ROLE_CREER_PRODUIT_CLE) || EntityRealm.getSubject().isPermitted(constante.ROLE_MODIFIER_PRODUIT)) {
            produit.setDate_creation(prod.getDate_creation());
            produit = prod; 
        } else {
            mtm.mikiLog4jMessageError();
        }    
    }
    
    public void annulerProduit() {
        produit = new Produit();
    }

    public Produit getProduit() {
        return produit;
    }

    public void setProduit(Produit produit) {
        this.produit = produit;
    }

    public Categorie getCategorie() {
        return categorie;
    }

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }

    public ProduitSessionBeanLocal getProduitServices() {
        return produitServices;
    }

    public void setProduitServices(ProduitSessionBeanLocal produitServices) {
        this.produitServices = produitServices;
    }

    public List<Produit> getProduitListe() {
        return produitServices.getAll("lib_prod", true);
    }

    public void setProduitListe(List<Produit> produitListe) {
        this.produitListe = produitListe;
    }

    public List<Categorie> getCategorieListe() {
        return categorieServices.getAll("lib_categ", true);
    }

    public void setCategorieListe(List<Categorie> categorieListe) {
        this.categorieListe = categorieListe;
    }

    public CategorieSessionBeanLocal getCategorieServices() {
        return categorieServices;
    }

    public void setCategorieServices(CategorieSessionBeanLocal categorieServices) {
        this.categorieServices = categorieServices;
    }

    public Mtm getmtm() {
        return mtm;
    }

    public void setmtm(Mtm mtm) {
        this.mtm = mtm;
    }
    
    
}
