/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fabrice.quickvente.web;

import com.fabrice.quickvente.entities.Categorie;
import com.fabrice.quickvente.entities.Prix;
import com.fabrice.quickvente.entities.Produit;
import com.fabrice.quickvente.entities.Produit_Prix;
import com.fabrice.quickvente.entities.Produit_Prix_id;
import com.fabrice.quickvente.services.CategorieSessionBeanLocal;
import com.fabrice.quickvente.services.PrixSessionBeanLocal;
import com.fabrice.quickvente.services.ProduitSessionBeanLocal;
import com.fabrice.quickvente.services.Produit_PrixSessionBeanLocal;
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
public class PrixManagedBean implements Serializable{
    
    private Produit produit;
    private Prix prix;
    private Categorie categorie;
    private Produit_Prix produit_Prix;
    private Produit_Prix_id produit_Prix_id;
    
    private List<Produit> produitListe;
    private List<Prix> prixListe;
    private List<Produit_Prix> produitprixListe;
    private List<Categorie> categorieListe;
        
    @EJB
    private ProduitSessionBeanLocal produitServices;
    
    @EJB
    private PrixSessionBeanLocal prixServices;
    
    @EJB
    private Produit_PrixSessionBeanLocal produitprixServices;
    
    @EJB
    private CategorieSessionBeanLocal categorieServices;
    
    public PrixManagedBean() {
        produit = new Produit();
        prix = new Prix();
        categorie = new Categorie();
        produit_Prix = new Produit_Prix();
        produit_Prix_id = new Produit_Prix_id();
        
        prixListe = new ArrayList<>();
        produitListe = new ArrayList<>();
        produitprixListe = new ArrayList<>();
        categorieListe = new  ArrayList<>();
        
    }
    
    public void gestionPrix() {
        if(EntityRealm.getSubject().isPermitted(constante.ROLE_CREER_PRIX_CLE)) {
            UserTransaction tx = TransactionManager.getUserTransaction();
            try {
                tx.begin();
                if(prix.getLib_prix().trim().isEmpty()) {
                    Mtm.mikiMessageWarn("Veuillez saisir le libelle du prix svp !");
                } else {
                    if(prix.getId_prix() == null) {
                        prixServices.saveOne(prix);
                        tx.commit();
                        prix = new Prix();
                        Mtm.mikiMessageInfo();
                    } else {
                        prixServices.updateOne(prix);
                        tx.commit();
                        prix = new Prix();
                        Mtm.mikiMessageInfo();
                    }
                }
            } catch (Exception ex) {
                try {
                    tx.rollback();
                } catch (IllegalStateException ex1) {
                    Logger.getLogger(ProduitManagedBean.class.getName()).log(Level.SEVERE, null, ex1);
                } catch (SecurityException ex1) {
                    Logger.getLogger(ProduitManagedBean.class.getName()).log(Level.SEVERE, null, ex1);
                } catch (SystemException ex1) {
                    Logger.getLogger(ProduitManagedBean.class.getName()).log(Level.SEVERE, null, ex1);
                }
                Mtm.mikiMessageError();                                
            }
        } else {
            Mtm.mikiLog4jMessageError();
        }
    }
    
    public void renvoiPrix(Prix pr) {
        prix = pr;
    }
    
    public void annulerPrix(){
        prix = new Prix();
    }
    
    public void gestionCategorie() {
       
        UserTransaction tx = TransactionManager.getUserTransaction();
        try {
            tx.begin();
            if(categorie.getLib_categ().trim().isEmpty()) {
                Mtm.mikiMessageWarn("Veuillez saisir le nom de la catégorie svp !");
            } else {
                if (categorie.getId_categ() == null) {
                    categorieServices.saveOne(categorie);
                    tx.commit();
                    categorie = new Categorie();
                    Mtm.mikiMessageInfo();
                } else {
                    categorieServices.updateOne(categorie);
                    tx.commit();
                    categorie = new Categorie();
                    Mtm.mikiMessageInfo();
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
            Mtm.mikiMessageError();
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
                    Mtm.mikiMessageWarn("Veuillez saisir le nom du produit svp !");
                } else if((produit.getQte_alerte() == 0) || produit.getQte_alerte().toString().trim().isEmpty()) {
                    Mtm.mikiMessageWarn("Veuillez saisir la quantité alerte du produit svp !");
                } else if(produit.getCategorie() == null) {
                    Mtm.mikiMessageWarn("Veuillez selectionner la catégorie du produit svp !");
                } else {
                    if(produit.getId_prod() == null) {
                        produit.setDate_creation(new Date());
                        produitServices.saveOne(produit);
                        tx.commit();
                        produit = new Produit();
                        Mtm.mikiMessageInfo();
                    } else {
                        produitServices.updateOne(produit);
                        tx.commit();
                        produit = new Produit();
                        Mtm.mikiMessageInfo();
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
                Mtm.mikiMessageError();                
            }
        } else {
            Mtm.mikiLog4jMessageError();
        }
    }
    
    public void renvoieProduit(Produit prod) {
        if(EntityRealm.getSubject().isPermitted(constante.ROLE_CREER_PRODUIT_CLE) || EntityRealm.getSubject().isPermitted(constante.ROLE_MODIFIER_PRODUIT)) {
            produit = prod;
        } else {
            Mtm.mikiLog4jMessageError();
        }    
    }
    
    public void annulerProduit() {
        produit = new Produit();
    }

    
    public void affectationPrix(){
        if(EntityRealm.getSubject().isPermitted(constante.ROLE_CREER_PRIX_CLE)) {
            UserTransaction tx = TransactionManager.getUserTransaction();
            try {                         
                tx.begin();
                if(produit_Prix.getProduit() == null) {
                    Mtm.mikiMessageWarn("Veuillez selectionner le produit svp !");
                } else if(produit_Prix.getPrix() == null) {
                    Mtm.mikiMessageWarn("Veuillez selectionner l'intitulé svp !");
                } else if((produit_Prix.getPrix_unit() == 0) || (produit_Prix.getPrix_unit().toString().trim().isEmpty())) {
                    Mtm.mikiMessageWarn("Veuillez saisir le prix unitaire du produit svp !");
                } else {
                    produit = produit_Prix.getProduit();
                    prix = produit_Prix.getPrix();
                    if(produitprixServices.getProduitPrixModif(produit, prix) == null) {
                        produit_Prix_id.setId_prix(prix.getId_prix());
                        produit_Prix_id.setId_prod(produit.getId_prod());
                        produit_Prix.setId(produit_Prix_id);
                        produit_Prix.setPrix(prix);
                        produit_Prix.setProduit(produit);
                        produitprixServices.saveOne(produit_Prix);
                        tx.commit();
                        produit = new Produit();
                        prix = new Prix();
                        produit_Prix = new Produit_Prix();
                        produit_Prix_id = new Produit_Prix_id();
                        Mtm.mikiMessageInfo();
                    } else {
                        Produit_Prix pp = produitprixServices.getProduitPrixModif(produit, prix);
                        produitprixServices.deleteOne(pp);
                        tx.commit();
                        produit_Prix_id.setId_prix(prix.getId_prix());
                        produit_Prix_id.setId_prod(produit.getId_prod());
                        produit_Prix.setId(produit_Prix_id);
                        produit_Prix.setPrix(prix);
                        produit_Prix.setProduit(produit);
                        produitprixServices.saveOne(produit_Prix);
                        produit = new Produit();
                        prix = new Prix();
                        produit_Prix = new Produit_Prix();
                        produit_Prix_id = new Produit_Prix_id();
                        Mtm.mikiMessageInfo();
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
                Mtm.mikiMessageError();                
            }
        } else {
            Mtm.mikiLog4jMessageError();
        }
        
    }
    
    public void renvoiProduit_prix(Produit_Prix pp){        
        if(EntityRealm.getSubject().isPermitted(constante.ROLE_CREER_PRIX_CLE)){                   
            produit_Prix = pp;
            Long pro = produit_Prix.getId().getId_prod();
            Long pri = produit_Prix.getId().getId_prix();             
            produit = produitServices.getOne(pro);
            prix = prixServices.getOne(pri);
        } else {
            Mtm.mikiLog4jMessageError();
        }
    }
    
    public void annulerProduit_prix(){
        produit_Prix = new Produit_Prix();
        produit = new Produit();
        prix = new Prix();
    }

    public Produit getProduit() {
        return produit;
    }

    public void setProduit(Produit produit) {
        this.produit = produit;
    }

    public Prix getPrix() {
        return prix;
    }

    public void setPrix(Prix prix) {
        this.prix = prix;
    }

    public Produit_Prix getProduit_Prix() {
        return produit_Prix;
    }

    public void setProduit_Prix(Produit_Prix produit_Prix) {
        this.produit_Prix = produit_Prix;
    }

    public Produit_Prix_id getProduit_Prix_id() {
        return produit_Prix_id;
    }

    public void setProduit_Prix_id(Produit_Prix_id produit_Prix_id) {
        this.produit_Prix_id = produit_Prix_id;
    }

    public List<Produit> getProduitListe() {
        return produitServices.getAll("lib_prod", true);
    }

    public void setProduitListe(List<Produit> produitListe) {
        this.produitListe = produitListe;
    }

    public List<Prix> getPrixListe() {
        return prixServices.getAll("lib_prix", true);
    }

    public void setPrixListe(List<Prix> prixListe) {
        this.prixListe = prixListe;
    }

    public List<Produit_Prix> getProduitprixListe() {
        return produitprixServices.getAll();
    }

    public void setProduitprixListe(List<Produit_Prix> produitprixListe) {
        this.produitprixListe = produitprixListe;
    }

    public ProduitSessionBeanLocal getProduitServices() {
        return produitServices;
    }

    public void setProduitServices(ProduitSessionBeanLocal produitServices) {
        this.produitServices = produitServices;
    }

    public PrixSessionBeanLocal getPrixServices() {
        return prixServices;
    }

    public void setPrixServices(PrixSessionBeanLocal prixServices) {
        this.prixServices = prixServices;
    }

    public Produit_PrixSessionBeanLocal getProduitprixServices() {
        return produitprixServices;
    }

    public void setProduitprixServices(Produit_PrixSessionBeanLocal produitprixServices) {
        this.produitprixServices = produitprixServices;
    }

    public Categorie getCategorie() {
        return categorie;
    }

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
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
    
    
}
