/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fabrice.quickvente.servicesImpl;

import com.fabrice.quickvente.services.CaissierSessionBeanLocal;
import com.fabrice.quickvente.core.dao.BaseDaoBean;
import com.fabrice.quickvente.core.service.BaseServiceBeanImpl;
import com.fabrice.quickvente.dao.ICaissier;
import com.fabrice.quickvente.entities.Caissier;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author FABRICE
 */
@Stateless
public class CaissierSessionBean extends BaseServiceBeanImpl<Caissier, Long>implements CaissierSessionBeanLocal {

    @EJB
    private ICaissier dao;

    @Override
    protected BaseDaoBean<Caissier, Long> getDao() {
        return dao;
    }
    
}
