/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fabrice.quickvente.daoImpl;

import com.fabrice.quickvente.core.dao.BaseDaoBeanImpl;
import com.fabrice.quickvente.dao.IInventaire;
import com.fabrice.quickvente.entities.Inventaire;
import java.io.Serializable;
import javax.ejb.Stateless;

/**
 *
 * @author FABRICE
 */
@Stateless
public class InventaireDao extends BaseDaoBeanImpl<Inventaire,Long> implements IInventaire{

    public InventaireDao() {
        super(Inventaire.class);
    }
        
}
