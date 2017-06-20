/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fabrice.quickvente.servicesImpl;

import com.fabrice.quickvente.core.dao.BaseDaoBean;
import com.fabrice.quickvente.core.service.BaseServiceBeanImpl;
import com.fabrice.quickvente.dao.IProduit;
import com.fabrice.quickvente.entities.Categorie;
import com.fabrice.quickvente.entities.Produit;
import com.fabrice.quickvente.services.ProduitSessionBeanLocal;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author FABRICE
 */
@Stateless
public class ProduitSessionBean extends BaseServiceBeanImpl<Produit, Long> implements ProduitSessionBeanLocal {

    @EJB
    private IProduit dao;

    @Override
    protected BaseDaoBean<Produit, Long> getDao() {
        return dao;
    
    }
    
    @Override
    public Integer getQuantiteDispo(Produit prod){
        return dao.getQuantiteDispo(prod);
    }
    
}
