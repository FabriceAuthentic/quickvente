/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fabrice.quickvente.servicesImpl;

import com.fabrice.quickvente.services.InventaireSessionBeanLocal;
import com.fabrice.quickvente.core.dao.BaseDaoBean;
import com.fabrice.quickvente.core.service.BaseServiceBeanImpl;
import com.fabrice.quickvente.dao.IInventaire;
import com.fabrice.quickvente.entities.Inventaire;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author FABRICE
 */
@Stateless
public class InventaireSessionBean extends BaseServiceBeanImpl<Inventaire, Long>implements InventaireSessionBeanLocal {

    @EJB
    private IInventaire dao;

    @Override
    protected BaseDaoBean<Inventaire, Long> getDao() {
        return dao;
    }
    
    
}
