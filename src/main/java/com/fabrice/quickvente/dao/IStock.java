/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fabrice.quickvente.dao;

import com.fabrice.quickvente.core.dao.BaseDaoBean;
import com.fabrice.quickvente.entities.Stock;
import java.io.Serializable;

/**
 *
 * @author FABRICE
 */
public interface IStock extends BaseDaoBean<Stock, Long>{
    public int getMax();
}
