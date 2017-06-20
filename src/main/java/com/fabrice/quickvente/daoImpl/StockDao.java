/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fabrice.quickvente.daoImpl;

import com.fabrice.quickvente.core.dao.BaseDaoBeanImpl;
import com.fabrice.quickvente.dao.IStock;
import com.fabrice.quickvente.entities.Stock;
import com.fabrice.webapp.core.Utils.Mtm;
import java.io.Serializable;
import javax.ejb.Stateless;

/**
 *
 * @author FABRICE
 */
@Stateless
public class StockDao extends BaseDaoBeanImpl<Stock, Long> implements IStock{

    public StockDao() {
        super(Stock.class);
    }
    
    @Override
    public int getMax() {
        try {
            return (int) em.createQuery("SELECT MAX(t.num_in) FROM Stock t").getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
            Mtm.mikiMessageError();           
            return 0;
        }
    }    
}
