/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fabrice.quickvente.daoImpl;

import com.fabrice.quickvente.core.dao.BaseDaoBeanImpl;
import com.fabrice.quickvente.dao.ILivraison;
import com.fabrice.quickvente.entities.Livraison;
import java.io.Serializable;
import javax.ejb.Stateless;

/**
 *
 * @author FABRICE
 */
@Stateless
public class LivraisonDao extends BaseDaoBeanImpl<Livraison, Long> implements ILivraison{
    
    public LivraisonDao(){
        super(Livraison.class);
    }
}
