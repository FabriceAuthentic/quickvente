/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fabrice.journal.DaoImpl;


import com.fabrice.journal.Dao.IJournalDAO;
import com.fabrice.journal.entities.Journal;
import com.fabrice.quickvente.core.dao.BaseDaoBeanImpl;
import javax.ejb.Stateless;

/**
 *
 * @author Mikel
 */
@Stateless
public class JournalDAO extends BaseDaoBeanImpl<Journal, Long> implements IJournalDAO {

    public JournalDAO() {
        super(Journal.class);
    }
    
}
