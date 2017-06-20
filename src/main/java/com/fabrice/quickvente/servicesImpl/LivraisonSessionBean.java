/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fabrice.quickvente.servicesImpl;

import com.fabrice.quickvente.core.dao.BaseDaoBean;
import com.fabrice.quickvente.core.dao.BaseDaoBeanImpl;
import com.fabrice.quickvente.core.service.BaseServiceBeanImpl;
import com.fabrice.quickvente.dao.ILivraison;
import com.fabrice.quickvente.entities.Livraison;
import com.fabrice.quickvente.services.LivraisonSessionBeanLocal;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author FABRICE
 */
@Stateless
public class LivraisonSessionBean extends BaseServiceBeanImpl<Livraison, Long> implements LivraisonSessionBeanLocal {
    
   @EJB
   private ILivraison dao;

    @Override
    protected BaseDaoBean<Livraison, Long> getDao() {
        return dao;
    }
   
   
}
