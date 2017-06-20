/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fabrice.quickvente.daoImpl;

import com.fabrice.quickvente.core.dao.BaseDaoBeanImpl;
import com.fabrice.quickvente.dao.IProduit;
import com.fabrice.quickvente.entities.Categorie;
import com.fabrice.quickvente.entities.Ligne_Vente;
import com.fabrice.quickvente.entities.Produit;
import com.fabrice.quickvente.entities.Stock;
import com.fabrice.quickvente.entities.Vente;
import com.fabrice.webapp.core.Utils.Mtm;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

/**
 *
 * @author FABRICE
 */
@Stateless
public class ProduitDao extends BaseDaoBeanImpl<Produit, Long> implements IProduit{
    
    public ProduitDao() {
        super(Produit.class);
    }
    
    @Override
    public Integer getQuantiteDispo(Produit prod){
        try {
            List<Stock> stockListe = new ArrayList<>();
            List<Ligne_Vente> ligneVenteListe = new ArrayList<>();
            
            TypedQuery<Stock> requete = em.createQuery("SELECT t FROM Stock t WHERE t.produit = :p", Stock.class);
            requete.setParameter("p", prod);
            stockListe = requete.getResultList();
            
            TypedQuery<Ligne_Vente> requete1 = em.createQuery("SELECT t FROM Ligne_Vente t WHERE t.produit = :v", Ligne_Vente.class);
            requete1.setParameter("v", prod);
            ligneVenteListe = requete1.getResultList();
            
            Integer qteEntree = 0;
            Integer qteVente = 0;
            
            for(Stock stk : stockListe){
                qteEntree = qteEntree + stk.getQte_stock();
            }
            
            for(Ligne_Vente lv : ligneVenteListe){
                qteVente = qteVente + lv.getQte_vente();
            }
            
            return qteEntree - qteVente;
            
        } catch (Exception e) {
            e.printStackTrace();
            Mtm.mikiMessageError();
            return null;
        }
    }
}
