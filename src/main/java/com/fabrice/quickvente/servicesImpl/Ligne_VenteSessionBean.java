/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fabrice.quickvente.servicesImpl;

import com.fabrice.quickvente.services.Ligne_VenteSessionBeanLocal;
import com.fabrice.quickvente.core.dao.BaseDaoBean;
import com.fabrice.quickvente.core.service.BaseServiceBeanImpl;
import com.fabrice.quickvente.dao.ILigne_Vente;
import com.fabrice.quickvente.entities.Ligne_Vente;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author FABRICE
 */
@Stateless
public class Ligne_VenteSessionBean extends BaseServiceBeanImpl<Ligne_Vente, Long>implements Ligne_VenteSessionBeanLocal {

    @EJB
    private ILigne_Vente dao;

    @Override
    protected BaseDaoBean<Ligne_Vente, Long> getDao() {
        return dao;
    }
        
}
