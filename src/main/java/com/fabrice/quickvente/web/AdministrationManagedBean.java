/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fabrice.quickvente.web;

import com.fabrice.quickvente.utils.constante;
import com.fabrice.webapp.core.Utils.Mtm;
import com.fabrice.webapp.core.Transaction.TransactionManager;
import com.fabrice.webapp.fabrice.securite.Service.DroitSessionBeanLocal;
import com.fabrice.webapp.fabrice.securite.Service.PossederSessionBeanLocal;
import com.fabrice.webapp.fabrice.securite.Service.PosteSessionBeanLocal;
import com.fabrice.webapp.fabrice.securite.Service.ProfilSessionBeanLocal;
import com.fabrice.webapp.fabrice.securite.Service.UtilisateurSessionBeanLocal;
import com.fabrice.webapp.fabrice.securite.entities.Droit;
import com.fabrice.webapp.fabrice.securite.entities.Posseder;
import com.fabrice.webapp.fabrice.securite.entities.PossederId;
import com.fabrice.webapp.fabrice.securite.entities.Poste;
import com.fabrice.webapp.fabrice.securite.entities.Profil;
import com.fabrice.webapp.fabrice.securite.entities.Utilisateur;
import com.fabrice.webapp.shiro.EntityRealm;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.imageio.stream.FileImageOutputStream;
import javax.imageio.stream.ImageOutputStream;
import javax.servlet.ServletContext;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;

/**
 *
 * @author Mikel
 */
@ManagedBean
@ViewScoped
public class AdministrationManagedBean implements Serializable {

    private Utilisateur utilisateur;
    private Utilisateur utilisateurTampon;
    private Utilisateur utilisateur2;
    private Utilisateur utilisateurProfil;
    private Utilisateur utilisateurPswd;
    private Utilisateur utilisateurExcel;
    private Profil profil;
    private Profil profilTampon;
    private Posseder posseder;
    private PossederId possederId;
    private Poste poste;
    private boolean disAdmin;
    private Mtm mtm;

    private List<Utilisateur> utilisateurListe;
    private List<Utilisateur> utilisateurListeSansAdmin;
    private List<Droit> droitListe;
    private List<Droit> droitListeSource;
    private List<Droit> droitListeSupp;
    private List<Profil> profilListe;
    private List<Poste> posteListe;
    private List<Posseder> possederSupp;

    public String tofProfil;
    public String cheminAbsolu;

    private InputStream inptStrm;

    @EJB
    private UtilisateurSessionBeanLocal utilisateurServices;

    @EJB
    private ProfilSessionBeanLocal profilServices;

    @EJB
    private PossederSessionBeanLocal possederServices;

    @EJB
    private PosteSessionBeanLocal posteServices;

    @EJB
    private DroitSessionBeanLocal droitServices;

    @ManagedProperty(value = "#{connexionManagedBean}")
    private ConnexionManagedBean connexionMngdB;

    public AdministrationManagedBean() {
        utilisateur = new Utilisateur();
        utilisateurTampon = new Utilisateur();
        utilisateur2 = new Utilisateur();
        utilisateurProfil = new Utilisateur();
        utilisateurPswd = new Utilisateur();
        utilisateurExcel = new Utilisateur();
        profil = new Profil();
        profilTampon = new Profil();
        posseder = new Posseder();
        possederId = new PossederId();
        poste = new Poste();

        utilisateurListe = new ArrayList<>();
        utilisateurListeSansAdmin = new ArrayList<>();
        droitListeSupp = new ArrayList<>();
        droitListe = new ArrayList<>();
        droitListeSource = new ArrayList<>();
        profilListe = new ArrayList<>();
        posteListe = new ArrayList<>();
        possederSupp = new ArrayList<>();

        tofProfil = "images/tofProfilDefaut.png";
        disAdmin = false;
        cheminAbsolu = "";
        mtm = new Mtm();

    }

    public void ajouterPoste() {
        UserTransaction tx = TransactionManager.getUserTransaction();
        try {
            tx.begin();
            if (poste.getLibPoste().isEmpty()) {
                mtm.mikiMessageWarnSaisir("le nom du poste");
            } else {
                if (poste.getIdLibPoste() == null) {
                    posteServices.saveOne(poste);
                    tx.commit();
                    poste = new Poste();
                    mtm.mikiMessageInfo();
                } else {
                    posteServices.updateOne(poste);
                    tx.commit();
                    poste = new Poste();
                    mtm.mikiMessageInfo();
                }

            }
        } catch (Exception ex) {
            try {
                tx.rollback();
            } catch (IllegalStateException ex1) {
                Logger.getLogger(AdministrationManagedBean.class.getName()).log(Level.SEVERE, null, ex1);
            } catch (SecurityException ex1) {
                Logger.getLogger(AdministrationManagedBean.class.getName()).log(Level.SEVERE, null, ex1);
            } catch (SystemException ex1) {
                Logger.getLogger(AdministrationManagedBean.class.getName()).log(Level.SEVERE, null, ex1);
            }
            mtm.mikiMessageError();
        }
    }

    public void renvoiePoste(Poste pe) {
        if (pe.getLibPoste().equals("Invite")) {
            mtm.mikiMessageErrorPerso("Impossible de modifier le poste Invite !");
        } else {
            poste = pe;
        }       
    }

    public void annulerPoste() {
        poste = new Poste();
    }

    public void affectationUtilisateurDroit() {
        if (EntityRealm.getSubject().isPermitted(constante.ROLE_GESTION_ADMINISTRATION_CLE)) {
            UserTransaction tx = TransactionManager.getUserTransaction();
            try {
                tx.begin();
                if (utilisateurProfil == null) {
                    mtm.mikiMessageWarnChoisir("l'utilisateur");
                } else if (utilisateurProfil.getProfil() == null) {
                    mtm.mikiMessageWarnSelectionner("le profil a affecter à l'utilisateur");
                } else {
                    utilisateurServices.updateOne(utilisateurProfil);
                    tx.commit();
                    mtm.logMikiLog4j(AdministrationManagedBean.class.getName(), org.apache.log4j.Level.INFO, "Affectation du profil : " + utilisateurProfil.getProfil().getNomProf() + ", à l'utilsateur : " + utilisateurProfil.getLogin());
                    utilisateurProfil = new Utilisateur();
                    mtm.mikiMessageInfo();
                }
            } catch (Exception ex) {
                try {
                    tx.rollback();
                } catch (IllegalStateException ex1) {
                    Logger.getLogger(AdministrationManagedBean.class.getName()).log(Level.SEVERE, null, ex1);
                } catch (SecurityException ex1) {
                    Logger.getLogger(AdministrationManagedBean.class.getName()).log(Level.SEVERE, null, ex1);
                } catch (SystemException ex1) {
                    Logger.getLogger(AdministrationManagedBean.class.getName()).log(Level.SEVERE, null, ex1);
                }
                mtm.mikiMessageError();
            }
        } else {
            mtm.mikiLog4jMessageError();
        }

    }

    public void renvoieUtilisateurDroit(Utilisateur ur) {
        if (EntityRealm.getSubject().isPermitted(constante.ROLE_GESTION_ADMINISTRATION_CLE)) {
            if (ur.getLogin().equals("Administrateur")) {
                mtm.mikiMessageErrorPerso("Impossible de modifier le profil de l'administrateur !");
            } else {
                utilisateurProfil = ur;
            }
        } else {
            mtm.mikiLog4jMessageError();
        }

    }

    public void annulerUtilisateurDroit() {
        utilisateurProfil = new Utilisateur();
    }

    public void ajouterUtilisateur() {
        if (EntityRealm.getSubject().isPermitted(constante.ROLE_GESTION_ADMINISTRATION_CLE)) {
            UserTransaction tx = TransactionManager.getUserTransaction();
            try {
                tx.begin();
                if (utilisateur.getPoste() == null) {
                    mtm.mikiMessageWarnChoisir("le poste");
                } else if (utilisateur.getNom().isEmpty()) {
                    mtm.mikiMessageWarnSaisir("le nom");
                } else if (utilisateur.getPrenom().isEmpty()) {
                    mtm.mikiMessageWarnSaisir("le prénom");
                } else if (utilisateur.getContact().isEmpty()) {
                    mtm.mikiMessageWarnSaisir("le contact");
                } else if (utilisateur.getAdresseEmail().isEmpty()) {
                    mtm.mikiMessageWarnSaisir("l'email");
                } else if (utilisateur.getLogin().isEmpty()) {
                    mtm.mikiMessageWarnSaisir("le nom d'utilisateur");
                } else {
                    if (utilisateur.getId() == null) {
                        if (utilisateurServices.getBy("login", utilisateur.getLogin()).isEmpty()) {
                            utilisateur.setMotDePasse(new Sha256Hash(constante.MOT_DE_PASSE_DEFAUT).toHex());
                            utilisateur.setPhoto(tofProfil);
                            utilisateur.setDateCreation(new Date());
                            utilisateur.setProfil(profilServices.getOneBy("nomProf", "Invite"));
                            utilisateur.setReinitialiserPswd(true);
                            utilisateurServices.saveOne(utilisateur);
                            tx.commit();
                            mtm.logMikiLog4j(AdministrationManagedBean.class.getName(), org.apache.log4j.Level.INFO, "Enregistrement d'un personnel :" + utilisateur.getNom() + " " + utilisateur.getPrenom());
                            utilisateur = new Utilisateur();
                            tofProfil = "images/tofProfilDefaut.png";
                            mtm.mikiMessageInfo();
                        } else {
                            mtm.mikiMessageErrorPerso("Ce nom d'utilisateur est déja utilisé, réessayez svp !");
                        }
                    } else {
                        utilisateurServices.updateOne(utilisateur);
                        tx.commit();

                        if (utilisateur.getNom().equals(utilisateurTampon.getNom()) && utilisateur.getPrenom().equals(utilisateurTampon.getPrenom())) {
                            mtm.logMikiLog4j(AdministrationManagedBean.class.getName(), org.apache.log4j.Level.INFO, "Modification des données du personnel :" + utilisateur.getNom() + " " + utilisateur.getPrenom());
                        } else {
                            mtm.logMikiLog4j(AdministrationManagedBean.class.getName(), org.apache.log4j.Level.INFO, "Modification de donnée d'un personnel :" + utilisateurTampon.getNom() + " " + utilisateurTampon.getPrenom() + ", par :"
                                    + utilisateur.getNom() + " " + utilisateur.getPrenom());
                        }
                        utilisateur = new Utilisateur();
                        utilisateurTampon = new Utilisateur();
                        tofProfil = "images/tofProfilDefaut.png";
                        mtm.mikiMessageInfo();
                    }
                }
            } catch (Exception ex) {
                try {
                    tx.rollback();
                } catch (IllegalStateException ex1) {
                    Logger.getLogger(AdministrationManagedBean.class.getName()).log(Level.SEVERE, null, ex1);
                } catch (SecurityException ex1) {
                    Logger.getLogger(AdministrationManagedBean.class.getName()).log(Level.SEVERE, null, ex1);
                } catch (SystemException ex1) {
                    Logger.getLogger(AdministrationManagedBean.class.getName()).log(Level.SEVERE, null, ex1);
                }
                mtm.logMikiLog4j(AdministrationManagedBean.class.getName(), org.apache.log4j.Level.ERROR, "Erreur survenue lors d'une opération sur le personnel :" + utilisateur.getNom() + " " + utilisateur.getPrenom());
                mtm.mikiMessageError();
            }
        } else {
            mtm.mikiLog4jMessageError();
        }

    }

    public void renvoieUtilisateur(Utilisateur ur) {
        if (EntityRealm.getSubject().isPermitted(constante.ROLE_GESTION_ADMINISTRATION_CLE)) {
            if (ur.getLogin().equals("Administrateur")) {
                if (connexionMngdB.getUserConnexionTest().getLogin().equals("Administrateur")) {
                    utilisateur = ur;
                    tofProfil = utilisateur.getPhoto();
                    utilisateurTampon = ur;
                    disAdmin = true;
                } else {
                    mtm.mikiMessageErrorPerso("Impossible de modifier les données de l'administrateur !");
                }
            } else {
                utilisateur = ur;
                tofProfil = utilisateur.getPhoto();
                utilisateurTampon = ur;
            }
        } else {
            mtm.mikiLog4jMessageError();
        }

    }

    public void annulerUtilisateur() {
        utilisateur = new Utilisateur();
        tofProfil = "images/tofProfilDefaut.png";
        disAdmin = false;
    }

    public void ajouterProfil() {
        if (EntityRealm.getSubject().isPermitted(constante.ROLE_GESTION_ADMINISTRATION_CLE)) {
            UserTransaction tx = TransactionManager.getUserTransaction();
            try {
                tx.begin();
                if (profil.getNomProf().trim().isEmpty()) {
                    mtm.mikiMessageWarnSaisir("le nom du profil");
                } else if (droitListeSource.isEmpty()) {
                    mtm.mikiMessageWarnSelectionner("le(s) droit(s)");
                } else {
                    if (profil.getIdProf() == null) {
                        profil.setDateCreaProf(new Date());
                        profilServices.saveOne(profil);
                        tx.commit();

                        List<Posseder> possederProfil;
                        possederProfil = new ArrayList<>();
                        for (Droit drt : droitListeSource) {
                            tx.begin();
                            possederId.setProfilID(profil.getIdProf());
                            possederId.setDroitUtilID(drt.getCodeDroit());
                            posseder.setId(possederId);
                            posseder.setProfil(profil);
                            posseder.setDroitUtilisateur(drt);

                            this.possederServices.saveOne(posseder);
                            tx.commit();

                            possederProfil.add(posseder);
                            posseder = new Posseder();
                            possederId = new PossederId();
                        }

                        for (Posseder po : possederProfil) {
                            tx.begin();
                            profil.ajouterPosseder(po);
                            profilServices.updateOne(profil);
                            tx.commit();
                        }

                        mtm.logMikiLog4j(AdministrationManagedBean.class.getName(), org.apache.log4j.Level.INFO, "Ajout d'un profil : " + profil.getNomProf());
                        profil = new Profil();
                        droitListeSource = new ArrayList<>();
                        mtm.mikiMessageInfo();
                    } else {
                        profilServices.updateOne(profil);
                        tx.commit();

                        for (Posseder drt1 : possederSupp) {
                            tx.begin();
                            possederServices.deleteOne(drt1.getId());
                            tx.commit();
                        }

                        List<Posseder> possederProfil3;
                        possederProfil3 = new ArrayList<>();
                        for (Posseder poss : profilTampon.getPosseders()) {
                            possederProfil3.add(poss);
                        }

                        for (Posseder poss2 : possederProfil3) {
                            tx.begin();
                            profilTampon.supprimerPosseder(poss2);
                            profilServices.updateOne(profilTampon);
                            tx.commit();
                        }

                        List<Posseder> possederProfil2;
                        possederProfil2 = new ArrayList<>();
                        for (Droit drt3 : droitListeSource) {
                            tx.begin();
                            possederId.setProfilID(profil.getIdProf());
                            possederId.setDroitUtilID(drt3.getCodeDroit());
                            posseder.setId(possederId);
                            posseder.setProfil(profil);
                            posseder.setDroitUtilisateur(drt3);

                            this.possederServices.saveOne(posseder);
                            tx.commit();

                            possederProfil2.add(posseder);
                            posseder = new Posseder();
                            possederId = new PossederId();
                        }

                        for (Posseder po : possederProfil2) {
                            tx.begin();
                            profil.ajouterPosseder(po);
                            profilServices.updateOne(profil);
                            tx.commit();
                        }
                        if (profil.getNomProf().equals(profilTampon.getNomProf())) {
                            mtm.logMikiLog4j(AdministrationManagedBean.class.getName(), org.apache.log4j.Level.INFO, "Modification des droits du profil :" + profil.getNomProf());
                        } else {
                            mtm.logMikiLog4j(AdministrationManagedBean.class.getName(), org.apache.log4j.Level.INFO, "Modification de donnée du profil :" + profilTampon.getNomProf() + ", par :" + profil.getNomProf());
                        }
                        profil = new Profil();
                        profilTampon = new Profil();
                        droitListeSource = new ArrayList<>();
                        possederSupp = new ArrayList<>();
                        mtm.mikiMessageInfo();
                    }

                }
            } catch (Exception ex) {
                try {
                    tx.rollback();
                } catch (IllegalStateException ex1) {
                    Logger.getLogger(AdministrationManagedBean.class.getName()).log(Level.SEVERE, null, ex1);
                } catch (SecurityException ex1) {
                    Logger.getLogger(AdministrationManagedBean.class.getName()).log(Level.SEVERE, null, ex1);
                } catch (SystemException ex1) {
                    Logger.getLogger(AdministrationManagedBean.class.getName()).log(Level.SEVERE, null, ex1);
                }
                mtm.logMikiLog4j(AdministrationManagedBean.class.getName(), org.apache.log4j.Level.ERROR, "Erreur survenue lors d'une opération sur le profil:" + profil.getNomProf());
                mtm.mikiMessageError();
            }
        } else {
            mtm.mikiLog4jMessageError();
        }

    }

    public void handleFileUpload(FileUploadEvent event) {
        try {
            String image = String.valueOf((int) (Math.random() * 10000000));
            ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
            String newFileName = servletContext.getRealPath("") + File.separator + "resources" + File.separator + "images" + File.separator + image + event.getFile().getFileName();
            InputStream inputStream = event.getFile().getInputstream();
            inptStrm = event.getFile().getInputstream();
            cheminAbsolu = "/resources/images/" + image + event.getFile().getFileName();
            ImageOutputStream out = new FileImageOutputStream(new File(newFileName));
            int read = 0;
            byte[] bytes = new byte[1024];
            while ((read = inputStream.read(bytes)) != -1) {
                out.write(bytes, 0, read);
            }
            inputStream.close();
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void importer() throws IOException {
        if (inptStrm != null) {
            ArrayList<String> values = new ArrayList<String>();
            try {
                InputStream input = inptStrm;
                XSSFWorkbook wb = new XSSFWorkbook(input);
                XSSFSheet sheet = wb.getSheetAt(0);
                XSSFRow v = sheet.getRow(0);
                Iterator rows = sheet.rowIterator();
                while (rows.hasNext()) {
                    values.clear();
                    XSSFRow row = (XSSFRow) rows.next();
                    //if (row.getRowNum() > 0) {
                    Iterator cells = row.cellIterator();

                    while (cells.hasNext()) {
                        XSSFCell cell = (XSSFCell) cells.next();

                        if (XSSFCell.CELL_TYPE_NUMERIC == cell.getCellType()) {
                            values.add(Integer.toString((int) cell.getNumericCellValue()));
                        } else if (XSSFCell.CELL_TYPE_STRING == cell.getCellType()) {
                            values.add(cell.getStringCellValue());
                        }
                    }

                    if (values.size() > 4) {
                        if (values.get(0).length() > 1 && values.get(1).length() > 1 && values.get(2).length() == 1 && values.get(3).matches("^[00228|+228]?[9|2][\\d]{7,}$") && values.get(4).matches("^[a-z0-9._-]+@[a-z0-9._-]{2,}[,|.][a-z]{2,4}$")) {
                            if (values.get(2).equals("M") || values.get(2).equals("F")) {
                                utilisateurExcel.setNom(values.get(0));
                                utilisateurExcel.setPrenom(values.get(1));
                                utilisateurExcel.setSexe(values.get(2));
                                utilisateurExcel.setContact(values.get(3));
                                utilisateurExcel.setAdresseEmail(values.get(4).replace(",", "."));
                                utilisateurExcel.setLogin(utilisateurExcel.getPrenom().charAt(0) + "." + utilisateurExcel.getNom());
                                if (utilisateurServices.getBy("login", utilisateurExcel.getLogin()).isEmpty()) {
                                    utilisateurExcel.setMotDePasse(new Sha256Hash(constante.MOT_DE_PASSE_DEFAUT).toHex());
                                    utilisateurExcel.setPhoto(tofProfil);
                                    utilisateurExcel.setDateCreation(new Date());
                                    utilisateurExcel.setPoste(posteServices.getOneBy("idLibPoste", Long.parseLong(String.valueOf(1))));
                                    utilisateurExcel.setProfil(profilServices.getOneBy("idProf", Long.parseLong(String.valueOf(2))));
                                    utilisateurExcel.setReinitialiserPswd(true);
                                    utilisateurServices.saveOne(utilisateurExcel);

                                    mtm.logMikiLog4j(AdministrationManagedBean.class.getName(), org.apache.log4j.Level.INFO, "Enregistrement d'un personnel :" + utilisateurExcel.getNom() + " " + utilisateurExcel.getPrenom());
                                    utilisateurExcel = new Utilisateur();
                                    tofProfil = "images/tofProfilDefaut.png";
                                } else {
                                    int test = 1;
                                    do {
                                        test++;
                                    } while (utilisateurServices.getBy("login", utilisateurExcel.getLogin() + "" + test).isEmpty() == false);
                                    utilisateurExcel.setMotDePasse(new Sha256Hash(constante.MOT_DE_PASSE_DEFAUT).toHex());
                                    utilisateurExcel.setPhoto(tofProfil);
                                    utilisateurExcel.setDateCreation(new Date());
                                    utilisateurExcel.setPoste(posteServices.getOneBy("idLibPoste", Long.parseLong(String.valueOf(1))));
                                    utilisateurExcel.setProfil(profilServices.getOneBy("idProf", Long.parseLong(String.valueOf(2))));
                                    utilisateurExcel.setReinitialiserPswd(true);
                                    utilisateurExcel.setLogin(utilisateurExcel.getLogin() + "" + test);
                                    utilisateurServices.saveOne(utilisateurExcel);

                                    mtm.logMikiLog4j(AdministrationManagedBean.class.getName(), org.apache.log4j.Level.INFO, "Enregistrement d'un personnel :" + utilisateurExcel.getNom() + " " + utilisateurExcel.getPrenom());
                                    utilisateurExcel = new Utilisateur();
                                    tofProfil = "images/tofProfilDefaut.png";

                                }
                            } else {
                                int nbrLigne1 = row.getRowNum() + 1;
                                mtm.mikiMessageWarn("Le sexe saisit à la ligne " + nbrLigne1 + " est incorrect , le sexe doit être M ou F svp!");
                            }
                        } else {
                            int nbrLigne2 = row.getRowNum() + 1;
                            mtm.mikiMessageWarn("Syntaxe de la ligne " + nbrLigne2 + " est incorrect !");
                        }
                    } else {
                        int nbrLigne = row.getRowNum() + 1;
                        mtm.mikiMessageWarn("Valeurs insuffisantes à la ligne " + nbrLigne + " !");
                        values.clear();
                    }
                }

                inptStrm = null;
            } catch (Exception ex) {
                System.out.println("Erreur");
                ex.printStackTrace();
            }
        } else {
            mtm.mikiMessageWarn("Veuillez choisir le fichier a importer svp !");
        }

    }

    public void annulerImporter() {
        inptStrm = null;
    }

    public void renvoieProfil(Profil pl) {
        if (EntityRealm.getSubject().isPermitted(constante.ROLE_GESTION_ADMINISTRATION_CLE)) {
            profil = pl;
            profilTampon = pl;
            for (Posseder drt : pl.getPosseders()) {
                droitListeSource.add(drt.getDroitUtilisateur());
            }
            possederSupp = possederServices.getBy("profil", pl);
            droitListeSupp = droitListeSource;
        } else {
            mtm.mikiLog4jMessageError();
        }
    }

    public void annulerProfil() {
        droitListeSource = new ArrayList<>();
        droitListeSupp = new ArrayList<>();
        profil = new Profil();
    }

    public void resetPasswordUser(Utilisateur ur) {
        System.out.println(ur);
        if (EntityRealm.getSubject().isPermitted(constante.ROLE_GESTION_ADMINISTRATION_CLE)) {
            try {
                utilisateurPswd = ur;
                if (utilisateurPswd != null) {
                    utilisateurPswd.setMotDePasse(new Sha256Hash(constante.MOT_DE_PASSE_DEFAUT).toHex());
                    utilisateurPswd.setReinitialiserPswd(true);
                    RequestContext.getCurrentInstance().execute("jQuery('#InfoReinit').modal('show', {backdrop: 'static'});");
                } else {
                    System.out.println("Erreur");
                }
            } catch (Exception e) {
                mtm.mikiMessageError();
                utilisateurPswd = new Utilisateur();
            }
        } else {
            mtm.mikiLog4jMessageError();
        }

    }

    public void confirmResetPasswordUser() {
        UserTransaction tx = TransactionManager.getUserTransaction();
        try {
            tx.begin();
            utilisateurServices.updateOne(utilisateurPswd);
            tx.commit();
            mtm.logMikiLog4j(AdministrationManagedBean.class.getName(), org.apache.log4j.Level.INFO, "Réinitialisation du mot de passe de l'utilisateur : " + utilisateurPswd.getLogin());
            utilisateurPswd = new Utilisateur();
            RequestContext.getCurrentInstance().execute("jQuery('#InfoReinit').modal('hide');");

            FacesMessage message3 = new FacesMessage(FacesMessage.SEVERITY_INFO, "Opération effectuée avec succès !", "");
            FacesContext.getCurrentInstance().addMessage(null, message3);
        } catch (Exception ex) {
            try {
                tx.rollback();
            } catch (IllegalStateException ex1) {
                Logger.getLogger(AdministrationManagedBean.class.getName()).log(Level.SEVERE, null, ex1);
            } catch (SecurityException ex1) {
                Logger.getLogger(AdministrationManagedBean.class.getName()).log(Level.SEVERE, null, ex1);
            } catch (SystemException ex1) {
                Logger.getLogger(AdministrationManagedBean.class.getName()).log(Level.SEVERE, null, ex1);
            }
            utilisateurPswd = new Utilisateur();
            RequestContext.getCurrentInstance().execute("jQuery('#InfoReinit').modal('hide');");
            mtm.logMikiLog4j(AdministrationManagedBean.class.getName(), org.apache.log4j.Level.ERROR, "Erreur survenue lors de la réinitialisation du mot de passe de l'utilisateur: " + utilisateurPswd.getLogin());
            mtm.mikiMessageError();
        }

    }

    public String actifLabel(boolean ac) {
        String testActif = ac ? "Actif" : "Inactif";
        return testActif;
    }

    public List<Droit> droitsUtilList(Profil grp) {
        List<Droit> droitUser = new ArrayList<>();

        try {
            if (grp.getPosseders().isEmpty() || grp.getPosseders() == null) {
                return null;
            } else {
                for (Posseder po : grp.getPosseders()) {
                    droitUser.add(po.getDroitUtilisateur());
                }
                return droitUser;
            }
        } catch (NullPointerException e) {
            return null;
        }

    }

    public void onRowSelect(SelectEvent event) {
        utilisateurProfil = utilisateur2;
        RequestContext.getCurrentInstance().execute("jQuery('#SearchUser').modal('hide');");
    }

    public void onRowUnselect(UnselectEvent event) {

    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    public Utilisateur getUtilisateurProfil() {
        return utilisateurProfil;
    }

    public void setUtilisateurProfil(Utilisateur utilisateurProfil) {
        this.utilisateurProfil = utilisateurProfil;
    }

    public Profil getProfil() {
        return profil;
    }

    public void setProfil(Profil profil) {
        this.profil = profil;
    }

    public Posseder getPosseder() {
        return posseder;
    }

    public void setPosseder(Posseder posseder) {
        this.posseder = posseder;
    }

    public PossederId getPossederId() {
        return possederId;
    }

    public void setPossederId(PossederId possederId) {
        this.possederId = possederId;
    }

    public Poste getPoste() {
        return poste;
    }

    public void setPoste(Poste poste) {
        this.poste = poste;
    }

    public List<Utilisateur> getUtilisateurListe() {
        return utilisateurServices.getAll("nom", true);
    }

    public void setUtilisateurListe(List<Utilisateur> utilisateurListe) {
        this.utilisateurListe = utilisateurListe;
    }

    public List<Droit> getDroitListe() {
        return droitServices.getNonBy("libDroit", "Tous");
    }

    public void setDroitListe(List<Droit> droitListe) {
        this.droitListe = droitListe;
    }

    public List<Droit> getDroitListeSource() {
        return droitListeSource;
    }

    public void setDroitListeSource(List<Droit> droitListeSource) {
        this.droitListeSource = droitListeSource;
    }

    public List<Profil> getProfilListe() {
        return profilServices.getNonBy("nomProf", "All_privilege");
    }

    public void setProfilListe(List<Profil> profilListe) {
        this.profilListe = profilListe;
    }

    public List<Poste> getPosteListe() {
        return posteServices.getAll("libPoste", true);
    }

    public void setPosteListe(List<Poste> posteListe) {
        this.posteListe = posteListe;
    }

    public UtilisateurSessionBeanLocal getUtilisateurServices() {
        return utilisateurServices;
    }

    public void setUtilisateurServices(UtilisateurSessionBeanLocal utilisateurServices) {
        this.utilisateurServices = utilisateurServices;
    }

    public ProfilSessionBeanLocal getProfilServices() {
        return profilServices;
    }

    public void setProfilServices(ProfilSessionBeanLocal profilServices) {
        this.profilServices = profilServices;
    }

    public PossederSessionBeanLocal getPossederServices() {
        return possederServices;
    }

    public void setPossederServices(PossederSessionBeanLocal possederServices) {
        this.possederServices = possederServices;
    }

    public PosteSessionBeanLocal getPosteServices() {
        return posteServices;
    }

    public void setPosteServices(PosteSessionBeanLocal posteServices) {
        this.posteServices = posteServices;
    }

    public DroitSessionBeanLocal getDroitServices() {
        return droitServices;
    }

    public void setDroitServices(DroitSessionBeanLocal droitServices) {
        this.droitServices = droitServices;
    }

    public List<Droit> getDroitListeSupp() {
        return droitListeSupp;
    }

    public void setDroitListeSupp(List<Droit> droitListeSupp) {
        this.droitListeSupp = droitListeSupp;
    }

    public String getTofProfil() {
        return tofProfil;
    }

    public void setTofProfil(String tofProfil) {
        this.tofProfil = tofProfil;
    }

    public Utilisateur getUtilisateurPswd() {
        return utilisateurPswd;
    }

    public void setUtilisateurPswd(Utilisateur utilisateurPswd) {
        this.utilisateurPswd = utilisateurPswd;
    }

    public Utilisateur getUtilisateur2() {
        return utilisateur2;
    }

    public void setUtilisateur2(Utilisateur utilisateur2) {
        this.utilisateur2 = utilisateur2;
    }

    public Utilisateur getUtilisateurTampon() {
        return utilisateurTampon;
    }

    public void setUtilisateurTampon(Utilisateur utilisateurTampon) {
        this.utilisateurTampon = utilisateurTampon;
    }

    public Profil getProfilTampon() {
        return profilTampon;
    }

    public void setProfilTampon(Profil profilTampon) {
        this.profilTampon = profilTampon;
    }

    public List<Utilisateur> getUtilisateurListeSansAdmin() {
        return utilisateurServices.getNonBy("login", "Administrateur", "login", true);
    }

    public void setUtilisateurListeSansAdmin(List<Utilisateur> utilisateurListeSansAdmin) {
        this.utilisateurListeSansAdmin = utilisateurListeSansAdmin;
    }

    public ConnexionManagedBean getConnexionMngdB() {
        return connexionMngdB;
    }

    public void setConnexionMngdB(ConnexionManagedBean connexionMngdB) {
        this.connexionMngdB = connexionMngdB;
    }

    public boolean isDisAdmin() {
        return disAdmin;
    }

    public void setDisAdmin(boolean disAdmin) {
        this.disAdmin = disAdmin;
    }

    public List<Posseder> getPossederSupp() {
        return possederSupp;
    }

    public void setPossederSupp(List<Posseder> possederSupp) {
        this.possederSupp = possederSupp;
    }

    public String getCheminAbsolu() {
        return cheminAbsolu;
    }

    public void setCheminAbsolu(String cheminAbsolu) {
        this.cheminAbsolu = cheminAbsolu;
    }

    public Utilisateur getUtilisateurExcel() {
        return utilisateurExcel;
    }

    public void setUtilisateurExcel(Utilisateur utilisateurExcel) {
        this.utilisateurExcel = utilisateurExcel;
    }

    public InputStream getInptStrm() {
        return inptStrm;
    }

    public void setInptStrm(InputStream inptStrm) {
        this.inptStrm = inptStrm;
    }

    public Mtm getMtm() {
        return mtm;
    }

    public void setMtm(Mtm mtm) {
        this.mtm = mtm;
    }
    
    

}
