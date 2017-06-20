/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fabrice.quickvente.web;

import com.fabrice.quickvente.entities.Categorie;
import com.fabrice.quickvente.entities.Livraison;
import com.fabrice.quickvente.entities.Produit;
import com.fabrice.quickvente.entities.Stock;
import com.fabrice.quickvente.entities.StockId;
import com.fabrice.quickvente.services.CategorieSessionBeanLocal;
import com.fabrice.quickvente.services.LivraisonSessionBeanLocal;
import com.fabrice.quickvente.services.ProduitSessionBeanLocal;
import com.fabrice.quickvente.services.StockSessionBeanLocal;
import com.fabrice.quickvente.utils.constante;
import com.fabrice.webapp.core.Transaction.TransactionManager;
import com.fabrice.webapp.core.Utils.Mtm;
import com.fabrice.webapp.shiro.EntityRealm;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;
import org.apache.log4j.Priority;
import org.primefaces.context.RequestContext;
import org.primefaces.event.CellEditEvent;
import org.primefaces.extensions.util.DateUtils;

/**
 *
 * @author FABRICE
 */
@ManagedBean
@ViewScoped
public class EntreeManagedBean implements Serializable{

    private Stock stock;
    private Produit produit;
    private Livraison livraison;
    private Categorie categorie;
    private StockId stockId;
    private Mtm mtm;
    
    private List<Stock> stockListe;
    private List<Stock> stockListe2;    
    private List<Produit> produitListe;
    private List<Produit> produitListeSelect;
    private List<Livraison> livraisonListe;
    private List<Categorie> categotieListe;    

    @EJB
    private StockSessionBeanLocal stockServices;
    
    @EJB
    private ProduitSessionBeanLocal produitServices;
    
    @EJB
    private LivraisonSessionBeanLocal livraisonServices;
    
    @EJB
    private CategorieSessionBeanLocal categorieServices;
                    
    public EntreeManagedBean() {
        produit = new Produit();
        stock = new Stock();
        livraison = new Livraison();
        categorie = new Categorie();
        stockId = new StockId();
        mtm = new Mtm();
        
        produitListe = new ArrayList<>();
        produitListeSelect = new ArrayList<>();
        categotieListe = new ArrayList<>();
        livraisonListe = new ArrayList<>();
        stockListe = new ArrayList<>();
        stockListe2 = new ArrayList<>();
        
    }
    
    public void checkDroit() {
        if (EntityRealm.getSubject().isPermitted(constante.ROLE_CREER_STOCK_CLE)) {
            RequestContext.getCurrentInstance().execute("jQuery('#entreeModal').modal('show', {backdrop: 'static'});");
        } else {
            Mtm.mikiLog4jMessageError();
        }
    }
    
    @PostConstruct
    public void init(){
        livraison.setDate_liv(new Date());
    }
    
    public void gestionCategorie(){
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
                Logger.getLogger(EntreeManagedBean.class.getName()).log(Level.SEVERE, null, ex1);
            } catch (SecurityException ex1) {
                Logger.getLogger(EntreeManagedBean.class.getName()).log(Level.SEVERE, null, ex1);
            } catch (SystemException ex1) {
                Logger.getLogger(EntreeManagedBean.class.getName()).log(Level.SEVERE, null, ex1);
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
        if(EntityRealm.getSubject().isPermitted(constante.ROLE_CREER_PRODUIT_CLE) || EntityRealm.getSubject().isPermitted(constante.ROLE_MODIFIER_PRODUIT)) {
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
                        mtm.logMikiLog4j(EntreeManagedBean.class.getName(), org.apache.log4j.Level.INFO, "Enregistrement du produit: " + produit.getLib_prod());
                        produit = new Produit();
                        mtm.mikiMessageInfo();
                    } else {
                        produitServices.updateOne(produit);
                        mtm.logMikiLog4j(EntreeManagedBean.class.getName(), org.apache.log4j.Level.INFO, "Modification du produit: " + produit.getLib_prod());
                        tx.commit();
                        produit = new Produit();
                        mtm.mikiMessageInfo();
                    }
                }
            } catch (Exception e) {
                try {
                    tx.rollback();
                } catch (IllegalStateException ex1) {
                    Logger.getLogger(EntreeManagedBean.class.getName()).log(Level.SEVERE, null, ex1);
                } catch (SecurityException ex1) {
                    Logger.getLogger(EntreeManagedBean.class.getName()).log(Level.SEVERE, null, ex1);
                } catch (SystemException ex1) {
                    Logger.getLogger(EntreeManagedBean.class.getName()).log(Level.SEVERE, null, ex1);
                }
                mtm.mikiMessageError();                
            }
        } else {
            mtm.mikiLog4jMessageError();
        }
    }
    
    public void renvoieProduit(Produit prod) {
        if(EntityRealm.getSubject().isPermitted(constante.ROLE_CREER_PRODUIT_CLE) || EntityRealm.getSubject().isPermitted(constante.ROLE_MODIFIER_PRODUIT)) {
            produit = prod; 
        } else {
            mtm.mikiLog4jMessageError();
        }    
    }
    
    public void annulerProduit() {
        produit = new Produit();
    }    
    
    public void ajoutProduit(){ 
        try {     
            System.out.println("-------------------------");
            for(Produit prd : produitListeSelect) {
                stock.setProduit(prd);
                System.out.println("Taille : "+stockListe.size());
                if(!stockListe.contains(stock)){
                    stockListe.add(stock);
                }
                stock = new Stock();
            }    
        } catch (Exception e) {
            mtm.mikiMessageError();
        }
    }
    
    public void onCellEdit(CellEditEvent event) {

    }    
    
    public void annulerListe(){
        produit = new Produit();
        stock = new Stock();
        livraison = new Livraison();
        stockListe.clear();
    }
    
    public void gestionLivraison(){
        if(EntityRealm.getSubject().isPermitted(constante.ROLE_CREER_STOCK_CLE)){
            UserTransaction tx = TransactionManager.getUserTransaction();
            try {
                tx.begin();
                if(livraison.getRef_liv().trim().isEmpty()){
                    mtm.mikiMessageWarn("Veuillez renseigner le numéro du bon de livraison svp!");
                } else if(livraison.getDate_liv() == null){
                    mtm.mikiMessageWarn("Veuillez renseigner la date de livraison svp!");
                } else {
                    if(livraison.getId_liv() == null){
                        livraisonServices.saveOne(livraison);
                        mtm.logMikiLog4j(EntreeManagedBean.class.getName(),org.apache.log4j.Level.INFO, "Enregistrement de livraison : " + livraison.getRef_liv());
                        tx.commit();
                        livraison = new Livraison();
                        livraison.setDate_liv(new Date());
                        mtm.mikiMessageInfo();
                    } else {
                        livraisonServices.updateOne(livraison);
                        mtm.logMikiLog4j(EntreeManagedBean.class.getName(),org.apache.log4j.Level.INFO, "Modification de livraison : " + livraison.getRef_liv());
                        tx.commit();
                        livraison = new Livraison();
                        mtm.mikiMessageInfo();
                    }
                }
            } catch (Exception e) {
                try {
                    tx.rollback();
                } catch (IllegalStateException ex1) {
                    Logger.getLogger(EntreeManagedBean.class.getName()).log(Level.SEVERE, null, ex1);
                } catch (SecurityException ex1) {
                    Logger.getLogger(EntreeManagedBean.class.getName()).log(Level.SEVERE, null, ex1);
                } catch (SystemException ex1) {
                    Logger.getLogger(EntreeManagedBean.class.getName()).log(Level.SEVERE, null, ex1);
                }
                mtm.mikiMessageError();                 
            }
        }else {
            mtm.mikiLog4jMessageError();
        }
    }
    
    public void annulerLivraison(){
        livraison = new Livraison();
    }
    
    public void renvoieLivraison(Livraison liv){
        livraison = liv;
    }
    
    public void gestionEntreeStock(){
        if(EntityRealm.getSubject().isPermitted(constante.ROLE_CONSULTER_STOCK_CLE)){
            UserTransaction tx = TransactionManager.getUserTransaction();
            try {                 
                tx.begin();
                if(livraison.getId_liv() == null){
                    mtm.mikiMessageWarn("Veuillez selectionnerÒ le numéro du bon de livraison");
                } else if(stockListe.isEmpty()){
                    Mtm.mikiMessageWarn("Veuillez ajouter un produit svp !");
                } else {
                    boolean test = false;
                    String nomProd = "";
                    
                    for(Stock st : stockListe) {
                        if((st.getQte_stock() == 0) || (st.getQte_stock().toString().trim().isEmpty())){
                            test = true;
                            nomProd = st.getProduit().getLib_prod();
                            break;
                        }
                    }
                    if(test) {
                        mtm.mikiMessageErrorPerso("Veuillez revoir la quantité stockée du produit : " + nomProd + " svp !");
                    } else {
                        for(Stock st : stockListe) {
                            tx.begin();
                            stockId.setId_liv(livraison.getId_liv());
                            stockId.setId_prod(st.getProduit().getId_prod());
                            st.setId(stockId);
                            st.setLivraison(livraison);
                            st.setProduit(st.getProduit());
                            stockServices.saveOne(st);
                            tx.commit();
                            stock = new Stock();
                        }
                        mtm.logMikiLog4j(EntreeManagedBean.class.getName(),org.apache.log4j.Level.INFO, "Réception de la livraison : " + livraison.getRef_liv());
                        stockListe = new ArrayList<>();
                        mtm.mikiMessageInfo();
                    }    
                }
            } catch (Exception e) {
                try {
                    tx.rollback();
                } catch (IllegalStateException ex1) {
                    Logger.getLogger(EntreeManagedBean.class.getName()).log(Level.SEVERE, null, ex1);
                } catch (SecurityException ex1) {
                    Logger.getLogger(EntreeManagedBean.class.getName()).log(Level.SEVERE, null, ex1);
                } catch (SystemException ex1) {
                    Logger.getLogger(EntreeManagedBean.class.getName()).log(Level.SEVERE, null, ex1);
                }
                Mtm.mikiMessageError();                                                
            }            
        }
    }

    public void supprimerStockListe(Stock stk){
        stockListe.remove(stk);
    }
    
    public boolean filterByDate(Object value, Object filter, Locale locale) {

        if( filter == null ) {
            return true;
        }

        if( value == null ) {
            return false;
        }
        
        return org.apache.commons.lang.time.DateUtils.truncatedEquals((Date) filter, (Date) value, Calendar.DATE);
    }

    public Livraison getLivraison() {
        return livraison;
    }

    public void setLivraison(Livraison livraison) {
        this.livraison = livraison;
    }

    public Categorie getCategorie() {
        return categorie;
    }

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }

    public Mtm getMtm() {
        return mtm;
    }

    public void setMtm(Mtm mtm) {
        this.mtm = mtm;
    }

    public List<Livraison> getLivraisonListe() {
        return livraisonServices.getAll("ref_liv", true);
    }

    public void setLivraisonListe(List<Livraison> livraisonListe) {
        this.livraisonListe = livraisonListe;
    }

    public List<Categorie> getCategotieListe() {
        return categotieListe;
    }

    public void setCategotieListe(List<Categorie> categotieListe) {
        this.categotieListe = categotieListe;
    }

    public LivraisonSessionBeanLocal getLivraisonServices() {
        return livraisonServices;
    }

    public void setLivraisonServices(LivraisonSessionBeanLocal livraisonServices) {
        this.livraisonServices = livraisonServices;
    }

    public CategorieSessionBeanLocal getCategorieServices() {
        return categorieServices;
    }

    public void setCategorieServices(CategorieSessionBeanLocal categorieServices) {
        this.categorieServices = categorieServices;
    }
    
    public List<Stock> getStockListe() {
        return stockListe;
    }

    public void setStockListe(List<Stock> stockListe) {
        this.stockListe = stockListe;
    }

    public Stock getStock() {
        return stock;
    }

    public void setStock(Stock stock) {
        this.stock = stock;
    }

    public Produit getProduit() {
        return produit;
    }

    public void setProduit(Produit produit) {
        this.produit = produit;
    }

    public List<Produit> getProduitListe() {
        return produitServices.getAll("lib_prod", true);
    }

    public void setProduitListe(List<Produit> produitListe) {
        this.produitListe = produitListe;
    }

    public StockSessionBeanLocal getStockServices() {
        return stockServices;
    }

    public void setStockServices(StockSessionBeanLocal stockServices) {
        this.stockServices = stockServices;
    }

    public ProduitSessionBeanLocal getProduitServices() {
        return produitServices;
    }

    public void setProduitServices(ProduitSessionBeanLocal produitServices) {
        this.produitServices = produitServices;
    }

    public List<Produit> getProduitListeSelect() {
        return produitListeSelect;
    }

    public void setProduitListeSelect(List<Produit> produitListeSelect) {
        this.produitListeSelect = produitListeSelect;
    }

    public StockId getStockId() {
        return stockId;
    }

    public void setStockId(StockId stockId) {
        this.stockId = stockId;
    }
    
    public Integer qteDispo(Produit prod){
        return produitServices.getQuantiteDispo(prod);
    }

    public List<Stock> getStockListe2() {
        return stockListe2;
    }

    public void setStockListe2(List<Stock> stockListe2) {
        this.stockListe2 = stockListe2;
    }
    
}
