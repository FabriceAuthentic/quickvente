/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fabrice.webapp.fabrice.securite.ServiceImpl;


import com.fabrice.quickvente.core.dao.BaseDaoBean;
import com.fabrice.quickvente.core.service.BaseServiceBeanImpl;
import com.fabrice.webapp.fabrice.securite.Dao.IDroitDAO;
import com.fabrice.webapp.fabrice.securite.Service.DroitSessionBeanLocal;
import com.fabrice.webapp.fabrice.securite.entities.Droit;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author Mikel
 */
@Stateless
public class DroitSessionBean extends BaseServiceBeanImpl<Droit, Long> implements DroitSessionBeanLocal {

    @EJB
    private IDroitDAO dao;

    @Override
    protected BaseDaoBean<Droit, Long> getDao() {
        return dao;
    }
}
