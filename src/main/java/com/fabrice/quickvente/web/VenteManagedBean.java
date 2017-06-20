/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fabrice.quickvente.web;

import com.fabrice.quickvente.entities.Categorie;
import com.fabrice.quickvente.entities.Client;
import com.fabrice.quickvente.entities.Ligne_Vente;
import com.fabrice.quickvente.entities.Prix;
import com.fabrice.quickvente.entities.Produit;
import com.fabrice.quickvente.entities.Produit_Prix;
import com.fabrice.quickvente.entities.Vente;
import com.fabrice.quickvente.services.CategorieSessionBeanLocal;
import com.fabrice.quickvente.services.ClientSessionBeanLocal;
import com.fabrice.quickvente.services.Ligne_VenteSessionBeanLocal;
import com.fabrice.quickvente.services.PrixSessionBeanLocal;
import com.fabrice.quickvente.services.ProduitSessionBeanLocal;
import com.fabrice.quickvente.services.Produit_PrixSessionBeanLocal;
import com.fabrice.quickvente.services.VenteSessionbeanLocal;
import com.fabrice.quickvente.utils.constante;
import com.fabrice.webapp.core.Transaction.TransactionManager;
import com.fabrice.webapp.core.Utils.Mtm;
import com.fabrice.webapp.shiro.EntityRealm;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author FABRICE
 */
@ManagedBean
@ViewScoped
public class VenteManagedBean implements Serializable{
    
    private Produit produit;
    private Categorie categorie;
    private Client client;
    private Vente vente;
    private Ligne_Vente ligne_Vente;
    private Prix prix, prixSelect;
    private int prixUnit;
    private Produit_Prix produit_Prix;
    private Mtm mtm;
    
    private List<Produit> produitListe;
    private List<Ligne_Vente> ligneVenteListe;
    private List<Vente> venteListe;
    private List<Categorie> categorieListe;
    private List<Client> clientListe;
    private List<Prix> prixListe;
    private List<Produit_Prix> produitPrixListe;
    
    @EJB
    private ProduitSessionBeanLocal produitServices;
    
    @EJB
    private ClientSessionBeanLocal clientServices;
    
    @EJB
    private PrixSessionBeanLocal prixServices;
    
    @EJB
    private VenteSessionbeanLocal venteServices;
    
    @EJB
    private Produit_PrixSessionBeanLocal produitPrixServices; 
    
    @EJB
    private CategorieSessionBeanLocal categorieServices;
    
    @EJB
    private Ligne_VenteSessionBeanLocal ligneVenteServices;
        
    public VenteManagedBean() {
        
        produit = new Produit();
        client = new Client();
        vente = new Vente();
        prix = new Prix();
        categorie = new Categorie();
        prixSelect = new Prix();       
        produit_Prix = new Produit_Prix();
        ligne_Vente = new Ligne_Vente();
        mtm = new Mtm();
        
        produitListe = new ArrayList<>();
        clientListe = new ArrayList<>();
        prixListe = new ArrayList<>();
        venteListe = new ArrayList<>();        
        produitPrixListe = new ArrayList<>();
        ligneVenteListe = new ArrayList<>();        
        categorieListe = new ArrayList<>();
    }
        
    public void gestionClient(){
        if(EntityRealm.getSubject().isPermitted(constante.ROLE_CONSULTER_CLIENT_CLE)) {
            UserTransaction tx = TransactionManager.getUserTransaction();
            try {
                tx.begin();
                if(client.getNom_cli().trim().isEmpty()) {
                    Mtm.mikiMessageWarn("Veuillez saisir le nom du client svp !");
                } else {
                    if (client.getId_cli() == null) {
                        clientServices.saveOne(client);
                        tx.commit();
                        client = new Client();
                        Mtm.mikiMessageInfo();
                    } else {
                        clientServices.updateOne(client);
                        tx.commit();
                        client = new Client();
                        Mtm.mikiMessageInfo();
                    } 
                }
            } catch (Exception e) {
                try {
                    tx.rollback();
                } catch (IllegalStateException ex1) {
                    Logger.getLogger(VenteManagedBean.class.getName()).log(Level.SEVERE, null, ex1);
                } catch (SecurityException ex1) {
                    Logger.getLogger(VenteManagedBean.class.getName()).log(Level.SEVERE, null, ex1);
                } catch (SystemException ex1) {
                    Logger.getLogger(VenteManagedBean.class.getName()).log(Level.SEVERE, null, ex1);
                }
                Mtm.mikiMessageError();                  
            }
        }
    }
    
    public void annulerClient(){
        client = new Client();
    }
    
    public void renvoieClient(Client cli){
        client = cli;
    }
    
    public void ajouterProduit(){
        if(EntityRealm.getSubject().isPermitted(constante.ROLE_CREER_VENTE_CLE)){
            try { 
                if(ligne_Vente.getProduit() == null){
                    mtm.mikiMessageWarn("Veuillez selectionner un produit svp !");
                }else if(ligne_Vente.getPrix() == null){
                    mtm.mikiMessageWarn("Veuillez selectionner un prix svp !");
                }else if((ligne_Vente.getQte_vente() == null) || (ligne_Vente.getQte_vente() == 0)){
                    mtm.mikiMessageWarn("Veuillez revoir la quantité commandée");
                }else{
                    if(!ligneVenteListe.contains(ligne_Vente)){
                        ligneVenteListe.add(ligne_Vente);
                    }
                }
            } catch (Exception e) {
                mtm.mikiMessageError();
            }
        }else{
            mtm.mikiLog4jMessageError();
        }
    }
    
    public void listenerPrix(){
        prix = ligne_Vente.getPrix();
        produit = ligne_Vente.getProduit();
        if(prix != null){
            try {
                prixUnit = produitPrixServices.getProduitPrixModif(produit, prix).getPrix_unit();
            } catch (Exception e) {
                mtm.mikiMessageWarn("Ton cu");
            }
        }else {
            prixUnit=0;
        }
    }
    
    public void annulerListe(){
        produit = new Produit();
        client = new Client();
        categorie = new Categorie();
        ligne_Vente.setQte_vente(0);
        categorieListe.clear();
        produitListe.clear();
        prixListe.clear();
    }
    
    public void supprimerVente(Ligne_Vente lv){
        ligneVenteListe.remove(lv);
    }
    
    public void listenerCateg(){
        produitListe = produitServices.getBy("categorie", categorie);
    }
    
    public Produit getProduit() {
        return produit;
    }

    public void setProduit(Produit produit) {
        this.produit = produit;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Vente getVente() {
        return vente;
    }

    public void setVente(Vente vente) {
        this.vente = vente;
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

    public List<Produit> getProduitListe() {
        return produitListe;
    }

    public void setProduitListe(List<Produit> produitListe) {
        this.produitListe = produitListe;
    }

    public List<Client> getClientListe() {
        return clientServices.getAll("nom_cli", true);
    }

    public void setClientListe(List<Client> clientListe) {
        this.clientListe = clientListe;
    }

    public List<Prix> getPrixListe() {
        return prixServices.getAll("lib_prix", true);
    }

    public void setPrixListe(List<Prix> prixListe) {
        this.prixListe = prixListe;
    }

    public List<Produit_Prix> getProduitPrixListe() {
        return produitPrixListe;
    }

    public void setProduitPrixListe(List<Produit_Prix> produitPrixListe) {
        this.produitPrixListe = produitPrixListe;
    }

    public Mtm getMtm() {
        return mtm;
    }

    public void setMtm(Mtm mtm) {
        this.mtm = mtm;
    }

    public ProduitSessionBeanLocal getProduitServices() {
        return produitServices;
    }

    public void setProduitServices(ProduitSessionBeanLocal produitServices) {
        this.produitServices = produitServices;
    }

    public ClientSessionBeanLocal getClientServices() {
        return clientServices;
    }

    public void setClientServices(ClientSessionBeanLocal clientServices) {
        this.clientServices = clientServices;
    }

    public PrixSessionBeanLocal getPrixServices() {
        return prixServices;
    }

    public void setPrixServices(PrixSessionBeanLocal prixServices) {
        this.prixServices = prixServices;
    }

    public VenteSessionbeanLocal getVenteServices() {
        return venteServices;
    }

    public void setVenteServices(VenteSessionbeanLocal venteServices) {
        this.venteServices = venteServices;
    }

    public Produit_PrixSessionBeanLocal getProduitPrixServices() {
        return produitPrixServices;
    }

    public void setProduitPrixServices(Produit_PrixSessionBeanLocal produitPrixServices) {
        this.produitPrixServices = produitPrixServices;
    }

    public Categorie getCategorie() {
        return categorie;
    }

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }

    public Prix getPrixSelect() {
        return prixSelect;
    }

    public void setPrixSelect(Prix prixSelect) {
        this.prixSelect = prixSelect;
    }

    public int getPrixUnit() {
        return prixUnit;
    }

    public void setPrixUnit(int prixUnit) {
        this.prixUnit = prixUnit;
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

    public List<Vente> getVenteListe() {
        return venteListe;
    }

    public void setVenteListe(List<Vente> venteListe) {
        this.venteListe = venteListe;
    }

    public Ligne_Vente getLigne_Vente() {
        return ligne_Vente;
    }

    public void setLigne_Vente(Ligne_Vente ligne_Vente) {
        this.ligne_Vente = ligne_Vente;
    }

    public List<Ligne_Vente> getLigneVenteListe() {
        return ligneVenteListe;
    }

    public void setLigneVenteListe(List<Ligne_Vente> ligneVenteListe) {
        this.ligneVenteListe = ligneVenteListe;
    }

    public Ligne_VenteSessionBeanLocal getLigneVenteServices() {
        return ligneVenteServices;
    }

    public void setLigneVenteServices(Ligne_VenteSessionBeanLocal ligneVenteServices) {
        this.ligneVenteServices = ligneVenteServices;
    }
    
    
}
