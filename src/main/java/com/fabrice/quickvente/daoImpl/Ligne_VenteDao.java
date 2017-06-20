/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fabrice.quickvente.daoImpl;

import com.fabrice.quickvente.core.dao.BaseDaoBeanImpl;
import com.fabrice.quickvente.dao.ILigne_Vente;
import com.fabrice.quickvente.entities.Ligne_Vente;
import java.io.Serializable;
import javax.ejb.Stateless;

/**
 *
 * @author FABRICE
 */
@Stateless
public class Ligne_VenteDao extends BaseDaoBeanImpl<Ligne_Vente, Long> implements ILigne_Vente{

    public Ligne_VenteDao() {
        super(Ligne_Vente.class);
    }
    
}
