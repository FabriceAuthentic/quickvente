/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fabrice.quickvente.servicesImpl;

import com.fabrice.quickvente.services.StockSessionBeanLocal;
import com.fabrice.quickvente.core.dao.BaseDaoBean;
import com.fabrice.quickvente.core.service.BaseServiceBeanImpl;
import com.fabrice.quickvente.dao.IStock;
import com.fabrice.quickvente.entities.Stock;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author FABRICE
 */
@Stateless
public class StockSessionBean extends BaseServiceBeanImpl<Stock, Long>implements StockSessionBeanLocal {

    @EJB
    private IStock dao;
    
    @Override
    protected BaseDaoBean<Stock, Long> getDao() {
        return dao;
    }

    @Override
    public int getMax() {
        return dao.getMax();
    }
    
}
