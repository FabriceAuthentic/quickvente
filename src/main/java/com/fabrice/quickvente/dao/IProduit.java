/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fabrice.quickvente.dao;

import com.fabrice.quickvente.core.dao.BaseDaoBean;
import com.fabrice.quickvente.entities.Categorie;
import com.fabrice.quickvente.entities.Produit;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author FABRICE
 */
public interface IProduit extends BaseDaoBean<Produit, Long> {
    
    public Integer getQuantiteDispo(Produit prod);
}
