/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fabrice.webapp.fabrice.securite.ServiceImpl;


import com.fabrice.quickvente.core.dao.BaseDaoBean;
import com.fabrice.quickvente.core.service.BaseServiceBeanImpl;
import com.fabrice.webapp.fabrice.securite.Dao.IProfilDAO;
import com.fabrice.webapp.fabrice.securite.Service.ProfilSessionBeanLocal;
import com.fabrice.webapp.fabrice.securite.entities.Profil;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author Mikel
 */
@Stateless
public class ProfilSessionBean extends BaseServiceBeanImpl<Profil, Long> implements ProfilSessionBeanLocal {

    @EJB
    private IProfilDAO dao;

    @Override
    protected BaseDaoBean<Profil, Long> getDao() {
        return dao;
    }
}
