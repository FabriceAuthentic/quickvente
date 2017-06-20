/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fabrice.webapp.fabrice.securite.Service;


import com.fabrice.quickvente.core.service.BaseServiceBean;
import com.fabrice.webapp.fabrice.securite.entities.Profil;
import javax.ejb.Local;

/**
 *
 * @author Mikel
 */
@Local
public interface ProfilSessionBeanLocal extends BaseServiceBean<Profil, Long>{
    
}
