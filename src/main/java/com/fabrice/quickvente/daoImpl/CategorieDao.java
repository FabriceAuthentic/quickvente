/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fabrice.quickvente.daoImpl;

import com.fabrice.quickvente.core.dao.BaseDaoBeanImpl;
import com.fabrice.quickvente.dao.ICategorie;
import com.fabrice.quickvente.entities.Categorie;
import java.io.Serializable;
import javax.ejb.Stateless;

/**
 *
 * @author FABRICE
 */
@Stateless
public class CategorieDao extends BaseDaoBeanImpl<Categorie, Long> implements ICategorie{
    
    public CategorieDao(){
        super(Categorie.class);
    }
}
