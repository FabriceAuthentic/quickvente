/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fabrice.webapp.fabrice.securite.DaoImlp;

import com.fabrice.quickvente.core.dao.BaseDaoBeanImpl;
import com.fabrice.webapp.fabrice.securite.Dao.IPossederDAO;
import com.fabrice.webapp.fabrice.securite.entities.Posseder;
import com.fabrice.webapp.fabrice.securite.entities.PossederId;
import javax.ejb.Stateless;



/**
 *
 * @author Mikel
 */
@Stateless
public class PossederDAO extends BaseDaoBeanImpl<Posseder, PossederId> implements IPossederDAO {

    public PossederDAO() {
        super(Posseder.class);
    }
    
}
