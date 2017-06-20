/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fabrice.quickvente.web;

import com.fabrice.quickvente.entities.Client;
import com.fabrice.quickvente.services.ClientSessionBeanLocal;
import com.fabrice.quickvente.utils.constante;
import com.fabrice.webapp.core.Transaction.TransactionManager;
import com.fabrice.webapp.core.Utils.Mtm;
import com.fabrice.webapp.shiro.EntityRealm;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

/**
 *
 * @author FABRICE
 */
@ManagedBean
@ViewScoped
public class ClientManagedBean implements Serializable {
    
    private Client client;
    
    private List<Client> clientListe;
    
    @EJB
    private ClientSessionBeanLocal clientServices;
    
    public ClientManagedBean() {
        
        client = new Client();
        
        clientListe = new ArrayList<>();
        
    }

    public void gestionClient() {
        
        if(EntityRealm.getSubject().isPermitted(constante.ROLE_CONSULTER_CLIENT_CLE)) {
            UserTransaction tx = TransactionManager.getUserTransaction();
            try {
                tx.begin();
                if(client.getNom_cli().trim().isEmpty()) {
                    Mtm.mikiMessageWarn("Veuillez saisir le nom du client svp !");
                } else {
                    if (client.getId_cli() == null) {
                        clientServices.saveOne(client);
                        tx.commit();
                        client = new Client();
                        Mtm.mikiMessageInfo();
                    } else {
                        clientServices.updateOne(client);
                        tx.commit();
                        client = new Client();
                        Mtm.mikiMessageInfo();
                    } 
                }
            } catch (Exception e) {
                try {
                    tx.rollback();
                } catch (IllegalStateException ex1) {
                    Logger.getLogger(ClientManagedBean.class.getName()).log(Level.SEVERE, null, ex1);
                } catch (SecurityException ex1) {
                    Logger.getLogger(ClientManagedBean.class.getName()).log(Level.SEVERE, null, ex1);
                } catch (SystemException ex1) {
                    Logger.getLogger(ClientManagedBean.class.getName()).log(Level.SEVERE, null, ex1);
                }
                Mtm.mikiMessageError();                  
            }
        }
    }
    
    public void renvoiClient(Client cli) {
        if(EntityRealm.getSubject().isPermitted(constante.ROLE_CONSULTER_CLIENT_CLE)) {
            client = cli;
        } else {
            Mtm.mikiLog4jMessageError();
        }
    }
    
    public void annulerClient() {
        client = new Client();
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public List<Client> getClientListe() {
        return clientServices.getAll("nom_cli", true);
    }

    public void setClientListe(List<Client> clientListe) {
        this.clientListe = clientListe;
    }

    public ClientSessionBeanLocal getClientservices() {
        return clientServices;
    }

    public void setClientservices(ClientSessionBeanLocal clientservices) {
        this.clientServices = clientservices;
    }
    
}
