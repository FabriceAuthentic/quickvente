/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fabrice.webapp.fabrice.securite.ServiceImpl;


import com.fabrice.quickvente.core.dao.BaseDaoBean;
import com.fabrice.quickvente.core.service.BaseServiceBeanImpl;
import com.fabrice.webapp.fabrice.securite.Dao.IPosteDAO;
import com.fabrice.webapp.fabrice.securite.Service.PosteSessionBeanLocal;
import com.fabrice.webapp.fabrice.securite.entities.Poste;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author Mikel
 */
@Stateless
public class PosteSessionBean extends BaseServiceBeanImpl<Poste, Long> implements PosteSessionBeanLocal {

    @EJB
    private IPosteDAO dao;

    @Override
    protected BaseDaoBean<Poste, Long> getDao() {
        return dao;
    }
}
