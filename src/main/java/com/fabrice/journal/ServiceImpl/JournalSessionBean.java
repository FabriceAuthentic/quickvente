/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fabrice.journal.ServiceImpl;


import com.fabrice.journal.Dao.IJournalDAO;
import com.fabrice.journal.Service.JournalSessionBeanLocal;
import com.fabrice.journal.entities.Journal;
import com.fabrice.quickvente.core.dao.BaseDaoBean;
import com.fabrice.quickvente.core.service.BaseServiceBeanImpl;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author Mikel
 */
@Stateless
public class JournalSessionBean extends BaseServiceBeanImpl<Journal, Long> implements JournalSessionBeanLocal {

    @EJB
    private IJournalDAO dao;

    @Override
    protected BaseDaoBean<Journal, Long> getDao() {
        return dao;
    }
}
