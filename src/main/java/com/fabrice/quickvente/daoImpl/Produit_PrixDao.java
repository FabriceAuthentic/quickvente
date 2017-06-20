/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fabrice.quickvente.daoImpl;

import com.fabrice.quickvente.core.dao.BaseDaoBeanImpl;
import com.fabrice.quickvente.dao.IProduit_Prix;
import com.fabrice.quickvente.entities.Prix;
import com.fabrice.quickvente.entities.Produit;
import com.fabrice.quickvente.entities.Produit_Prix;
import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

/**
 *
 * @author FABRICE
 */
@Stateless
public class Produit_PrixDao extends BaseDaoBeanImpl<Produit_Prix, Long> implements IProduit_Prix{

    public Produit_PrixDao() {
       super(Produit_Prix.class);
    }
    
    @Override
    public Produit_Prix getProduitPrixModif(Produit prod, Prix prx){
        try {
            Produit_Prix PP = new Produit_Prix();
            
            TypedQuery<Produit_Prix> requete = em.createQuery("SELECT t FROM Produit_Prix t WHERE t.produit = :p AND t.prix = :pr", Produit_Prix.class);
            requete.setParameter("p", prod);
            requete.setParameter("pr", prx);
            
            PP = requete.getSingleResult();
            
            return PP;
            
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }    
}
