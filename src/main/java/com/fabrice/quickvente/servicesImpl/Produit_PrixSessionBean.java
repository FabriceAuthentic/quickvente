/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fabrice.quickvente.servicesImpl;

import com.fabrice.quickvente.services.Produit_PrixSessionBeanLocal;
import com.fabrice.quickvente.core.dao.BaseDaoBean;
import com.fabrice.quickvente.core.service.BaseServiceBeanImpl;
import com.fabrice.quickvente.dao.IProduit_Prix;
import com.fabrice.quickvente.entities.Prix;
import com.fabrice.quickvente.entities.Produit;
import com.fabrice.quickvente.entities.Produit_Prix;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author FABRICE
 */
@Stateless
public class Produit_PrixSessionBean extends BaseServiceBeanImpl<Produit_Prix, Long> implements Produit_PrixSessionBeanLocal {

    @EJB
    private IProduit_Prix dao;

    @Override
    protected BaseDaoBean<Produit_Prix, Long> getDao() {
        return dao;
    }
    
    @Override
    public Produit_Prix getProduitPrixModif(Produit prod, Prix prx){
        return dao.getProduitPrixModif(prod, prx);
    }
}
