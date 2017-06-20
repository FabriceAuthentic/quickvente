/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fabrice.quickvente.servicesImpl;

import com.fabrice.quickvente.core.dao.BaseDaoBean;
import com.fabrice.quickvente.core.service.BaseServiceBeanImpl;
import com.fabrice.quickvente.dao.IPrix;
import com.fabrice.quickvente.entities.Prix;
import com.fabrice.quickvente.services.PrixSessionBeanLocal;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author FABRICE
 */
@Stateless
public class PrixSessionBean extends BaseServiceBeanImpl<Prix, Long> implements PrixSessionBeanLocal {

    @EJB
    private IPrix dao;

    @Override
    protected BaseDaoBean<Prix, Long> getDao() {
        return dao;
    }
    
    
}
