/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fabrice.quickvente.servicesImpl;

import com.fabrice.quickvente.core.dao.BaseDaoBean;
import com.fabrice.quickvente.core.service.BaseServiceBeanImpl;
import com.fabrice.quickvente.dao.IVente;
import com.fabrice.quickvente.daoImpl.VenteDao;
import com.fabrice.quickvente.entities.Vente;
import com.fabrice.quickvente.services.VenteSessionbeanLocal;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author FABRICE
 */
@Stateless
public class VenteSessionbean extends BaseServiceBeanImpl<Vente, Long> implements VenteSessionbeanLocal {

    @EJB
    private IVente dao;
    
    @Override
    protected BaseDaoBean<Vente, Long> getDao() {
       return dao;
    }
    
    
}
