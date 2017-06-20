/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fabrice.quickvente.servicesImpl;

import com.fabrice.quickvente.services.CategorieSessionBeanLocal;
import com.fabrice.quickvente.core.dao.BaseDaoBean;
import com.fabrice.quickvente.core.service.BaseServiceBeanImpl;
import com.fabrice.quickvente.dao.ICategorie;
import com.fabrice.quickvente.entities.Categorie;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author FABRICE
 */
@Stateless
public class CategorieSessionBean extends BaseServiceBeanImpl<Categorie, Long> implements CategorieSessionBeanLocal {

    @EJB
    private ICategorie dao;

    @Override
    protected BaseDaoBean<Categorie, Long> getDao() {
        return dao;
    }
    
}
