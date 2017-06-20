/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fabrice.webapp.fabrice.securite.ServiceImpl;


import com.fabrice.quickvente.core.dao.BaseDaoBean;
import com.fabrice.quickvente.core.service.BaseServiceBeanImpl;
import com.fabrice.webapp.fabrice.securite.Dao.IPossederDAO;
import com.fabrice.webapp.fabrice.securite.Service.PossederSessionBeanLocal;
import com.fabrice.webapp.fabrice.securite.entities.Posseder;
import com.fabrice.webapp.fabrice.securite.entities.PossederId;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author Mikel
 */
@Stateless
public class PossederSessionBean extends BaseServiceBeanImpl<Posseder, PossederId> implements PossederSessionBeanLocal {

    @EJB
    private IPossederDAO dao;

    @Override
    protected BaseDaoBean<Posseder, PossederId> getDao() {
        return dao;
    }
}
