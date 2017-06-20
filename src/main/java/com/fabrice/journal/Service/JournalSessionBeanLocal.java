/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fabrice.journal.Service;


import com.fabrice.journal.entities.Journal;
import com.fabrice.quickvente.core.service.BaseServiceBean;
import javax.ejb.Local;

/**
 *
 * @author Mikel
 */
@Local
public interface JournalSessionBeanLocal extends BaseServiceBean<Journal, Long>{
    
}
