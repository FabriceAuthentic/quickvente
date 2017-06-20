/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fabrice.quickvente.web;


import com.fabrice.journal.Service.JournalSessionBeanLocal;
import com.fabrice.journal.entities.Journal;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.context.RequestContext;

/**
 *
 * @author Mikel
 */
@ManagedBean
@ViewScoped
public class JournalManagedBean implements Serializable{

    private Journal journal;
   private List<Journal> journalListe;
   
   @EJB
   private JournalSessionBeanLocal journalServices;
   
    public JournalManagedBean() {
        journalListe = new ArrayList<>();   
        journal = new Journal();
    }
    
    public void renvoieJournal(Journal jl){
        journal = jl;
        RequestContext.getCurrentInstance().execute("jQuery('#journalModal').modal('show', {backdrop: 'static'});");
        
    }

    public List<Journal> getJournalListe() {
        return journalServices.getNonBy("logger","org.apache.shiro.realm.AuthorizingRealm","idJourn", false);
    }

    public void setJournalListe(List<Journal> journalListe) {
        this.journalListe = journalListe;
    }

    public JournalSessionBeanLocal getJournalServices() {
        return journalServices;
    }

    public void setJournalServices(JournalSessionBeanLocal journalServices) {
        this.journalServices = journalServices;
    }

    public Journal getJournal() {
        return journal;
    }

    public void setJournal(Journal journal) {
        this.journal = journal;
    }
    
    
    
}
