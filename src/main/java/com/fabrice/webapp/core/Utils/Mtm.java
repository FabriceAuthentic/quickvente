/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fabrice.webapp.core.Utils;

import java.io.File;
import java.net.URL;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.apache.log4j.Logger;
import org.apache.log4j.Priority;
import org.apache.log4j.xml.DOMConfigurator;

/**
 *
 * @author Mikel
 */
public class Mtm {

    /**
     * 
     * @param miki le message à afficher en mode severity_warn
     */
    public static void mikiMessageWarn(String miki) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, miki, "");
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    /**
     * 
     * @param miki le message à afficher en mode severity_info
     */
    public static void mikiMessageInfoPerso(String miki) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, miki, "");
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
    
    /**
     * 
     * @param miki le message à afficher en severity_warn genre Veuillez saisir "+miki+" svp !
     */
    public static void mikiMessageWarnSaisir(String miki) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Veuillez saisir "+miki+" svp !", "");
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
    
    /**
     * 
     * @param miki le message à afficher en severity_warn genre Veuillez choisir "+miki+" svp !
     */
    public static void mikiMessageWarnChoisir(String miki) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Veuillez choisir "+miki+" svp !", "");
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
    
    /**
     * 
     * @param miki le message à afficher en severity_warn genre Veuillez sélectionner "+miki+" svp !
     */
    public static void mikiMessageWarnSelectionner(String miki) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Veuillez sélectionner "+miki+" svp !", "");
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    /**
     *  Message à afficher en severity_info genre Opération effectuée avec succès !
     */
    public static void mikiMessageInfo() {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Opération effectuée avec succès !", "");
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    /**
     * Message à afficher en severity_error genre Echec de l'opération
     */
    public static void mikiMessageError() {
        FacesMessage message2 = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Echec de l'opération", "");
        FacesContext.getCurrentInstance().addMessage(null, message2);
    }
    
    
    /**
     * 
     * @param miki le message à afficher en mode severity_error
     */
    public static void mikiMessageErrorPerso(String miki) {
        FacesMessage message2 = new FacesMessage(FacesMessage.SEVERITY_ERROR, miki, "");
        FacesContext.getCurrentInstance().addMessage(null, message2);
    }

    /**
     * Message à afficher en severity_warn genre Opération interrompue : Absence de droit permettant d'effectuer cette opération, veuillez contacter l'administrateur Svp !
     */
    public static void mikiLog4jMessageError() {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Opération interrompue : Absence de droit permettant d'effectuer cette opération, veuillez contacter l'administrateur Svp !", "");
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    /**
     * Enregistre dans le journal grace a log4j
     * @param loggerName le nom de la classe concernée
     * @param priority priorité
     * @param message le message qui suit le log
     */
    public void logMikiLog4j(String loggerName, Priority priority, String message) {
        try {
            Logger loggerMiki = Logger.getLogger(loggerName);
            URL u = getClass().getClassLoader().getResource("log4j.xml");
            DOMConfigurator.configure(u);
            loggerMiki.log(priority, message);
        } catch (Exception e) {
            Mtm.mikiMessageError();
        }

    }
   

    public static void mikiWebAppDirectory(){
        try {
            File fl = new File("C:\\cabinetmekanmanpene-webapp");
            if (!fl.exists() && !fl.isDirectory()) {
                fl.mkdirs();
            }
        } catch (Exception e) {
            Mtm.mikiMessageError();
        }
    }
    /**
    *Supprime les fichiers chargés sur le serveur lorsque l'opération est annulé 
    *
    */
    public static void mikiSuppFileInDirectory(){
        try {
            File fl = new File("\\resources\\images\\7943160Validation_3_année.moo");
            System.out.println(fl);
            if (fl.exists()) {
                fl.delete();
            }
        } catch (Exception e) {
            Mtm.mikiMessageError();
        }
    }

}
