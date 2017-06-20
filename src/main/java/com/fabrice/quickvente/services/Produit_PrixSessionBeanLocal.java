/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fabrice.quickvente.services;

import com.fabrice.quickvente.core.service.BaseServiceBean;
import com.fabrice.quickvente.entities.Prix;
import com.fabrice.quickvente.entities.Produit;
import com.fabrice.quickvente.entities.Produit_Prix;
import java.io.Serializable;
import javax.ejb.Local;

/**
 *
 * @author FABRICE
 */
@Local
public interface Produit_PrixSessionBeanLocal extends BaseServiceBean<Produit_Prix, Long>{

    public Produit_Prix getProduitPrixModif(Produit Prod, Prix Prx);
}
