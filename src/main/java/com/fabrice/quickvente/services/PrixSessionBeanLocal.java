/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fabrice.quickvente.services;

import com.fabrice.quickvente.core.service.BaseServiceBean;
import com.fabrice.quickvente.entities.Prix;
import java.io.Serializable;
import javax.ejb.Local;

/**
 *
 * @author FABRICE
 */
@Local
public interface PrixSessionBeanLocal extends BaseServiceBean<Prix, Long>{
    
}
