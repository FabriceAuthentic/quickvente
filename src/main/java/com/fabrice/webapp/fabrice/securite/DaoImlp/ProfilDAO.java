/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fabrice.webapp.fabrice.securite.DaoImlp;


import com.fabrice.quickvente.core.dao.BaseDaoBeanImpl;
import com.fabrice.webapp.fabrice.securite.Dao.IProfilDAO;
import com.fabrice.webapp.fabrice.securite.entities.Profil;
import javax.ejb.Stateless;

/**
 *
 * @author Mikel
 */
@Stateless
public class ProfilDAO extends BaseDaoBeanImpl<Profil, Long> implements IProfilDAO {

    public ProfilDAO() {
        super(Profil.class);
    }
    
}
