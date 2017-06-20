/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fabrice.quickvente.servicesImpl;

import com.fabrice.quickvente.services.ClientSessionBeanLocal;
import com.fabrice.quickvente.core.dao.BaseDaoBean;
import com.fabrice.quickvente.core.service.BaseServiceBeanImpl;
import com.fabrice.quickvente.dao.IClient;
import com.fabrice.quickvente.entities.Client;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author FABRICE
 */
@Stateless
public class ClientSessionBean extends BaseServiceBeanImpl<Client, Long> implements ClientSessionBeanLocal {

    @EJB
    private IClient dao;

    @Override
    protected BaseDaoBean<Client, Long> getDao() {
        return dao;
    }
  
   
}
