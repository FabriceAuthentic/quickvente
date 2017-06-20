/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fabrice.webapp.fabrice.securite.ServiceImpl;


import com.fabrice.quickvente.core.dao.BaseDaoBean;
import com.fabrice.quickvente.core.service.BaseServiceBeanImpl;
import com.fabrice.webapp.fabrice.securite.Dao.IUtilisateurDAO;
import com.fabrice.webapp.fabrice.securite.Service.UtilisateurSessionBeanLocal;
import com.fabrice.webapp.fabrice.securite.entities.Utilisateur;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author Mikel
 */
@Stateless
public class UtilisateurSessionBean extends BaseServiceBeanImpl<Utilisateur, Long> implements UtilisateurSessionBeanLocal {

    @EJB
    private IUtilisateurDAO dao;

    @Override
    protected BaseDaoBean<Utilisateur, Long> getDao() {
        return dao;
    }
}
