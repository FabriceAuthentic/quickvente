/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fabrice.quickvente.web;



import com.fabrice.webapp.core.Utils.Mtm;
import com.fabrice.webapp.shiro.EntityRealm;
import java.io.IOException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import org.apache.log4j.Level;
import org.omnifaces.util.Faces;
//import org.omnifaces.util.Faces;

/**
 *
 * @author Mikel
 */

@ManagedBean
@RequestScoped
public class LogoutManagedBean{
    
    private Mtm mtm;
       
    public void deconnexionUser() throws IOException {
        mtm = new Mtm();
        mtm.logMikiLog4j(LogoutManagedBean.class.getName(), Level.INFO, "Deconnexion");
        EntityRealm.getSubject().logout();
        Faces.invalidateSession();
        Faces.redirect("index.xhtml");
    }
    
}
