/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fabrice.quickvente.web;

import com.fabrice.quickvente.utils.constante;
import com.fabrice.journal.Service.JournalSessionBeanLocal;
import com.fabrice.webapp.core.Utils.Mtm;
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
import java.io.IOException;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import org.apache.log4j.Level;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.apache.shiro.web.util.SavedRequest;
import org.apache.shiro.web.util.WebUtils;
import org.omnifaces.util.Faces;
//import org.omnifaces.util.Faces;
import org.primefaces.context.RequestContext;

/**
 *
 * @author Mikel
 */
@ManagedBean(name = "connexionManagedBean")
@SessionScoped
public class ConnexionManagedBean implements Serializable {

    private Utilisateur userConnexion, userConnexionTest, user;
    private Profil profilUtilisateur;
    private Profil profil2;
    private Posseder posseder;
    private PossederId possederId;
    private List<Droit> droitUtilisateurs2;
    private List<Poste> postes;
    private List<Profil> profilList;
    private List<Utilisateur> users;
    private Poste poste;
    private String tofProfil;
    private String userProfil;
    private String user1;
    private String userPoste;
    private Utilisateur persUser;
    private Droit droitTous;
    private String newMdp;
    private String comfMdp;
    private Mtm mtm;

    @EJB
    private DroitSessionBeanLocal droitUtilisateurServices;
    @EJB
    private UtilisateurSessionBeanLocal userServices;
    @EJB
    private UtilisateurSessionBeanLocal utilisateurServices;
    @EJB
    private PossederSessionBeanLocal possederServices;
    @EJB
    private ProfilSessionBeanLocal profilUtilisateurServices;
    @EJB
    private PosteSessionBeanLocal posteServices;
    @EJB
    private JournalSessionBeanLocal journalServices;

    public ConnexionManagedBean() {
        userConnexion = new Utilisateur();
        userConnexionTest = new Utilisateur();
        persUser = new Utilisateur();
        user = new Utilisateur();
        posseder = new Posseder();
        possederId = new PossederId();
        profilUtilisateur = new Profil();
        profil2 = new Profil();
        droitTous = new Droit();
        droitUtilisateurs2 = new ArrayList<>();
        tofProfil = "images/tofProfilDefaut.png";
        postes = new ArrayList<>();
        profilList = new ArrayList<>();
        users = new ArrayList<>();
        poste = new Poste();
        mtm = new Mtm();
    }

    @PostConstruct
    public void init() {
        List<Droit> droitUsers = droitUtilisateurServices.getAll();

        if (droitUsers.isEmpty()) {
            droitUtilisateurServices.saveOne(new Droit(constante.ROLE_ALL, constante.ROLE_ALL_CLE));
            droitUtilisateurServices.saveOne(new Droit(constante.ROLE_GESTION_ADMINISTRATION, constante.ROLE_GESTION_ADMINISTRATION_CLE));
            droitUtilisateurServices.saveOne(new Droit(constante.ROLE_INVENTAIRE, constante.ROLE_INVENTAIRE_CLE));
            droitUtilisateurServices.saveOne(new Droit(constante.ROLE_CONSULTER_VENTE, constante.ROLE_CONSULTER_VENTE_CLE));
            droitUtilisateurServices.saveOne(new Droit(constante.ROLE_CONSULTER_PRODUIT, constante.ROLE_CONSULTER_PRODUIT_CLE));
            droitUtilisateurServices.saveOne(new Droit(constante.ROLE_CONSULTER_STOCK, constante.ROLE_CONSULTER_STOCK_CLE));
            droitUtilisateurServices.saveOne(new Droit(constante.ROLE_CREER_VENTE, constante.ROLE_CREER_VENTE_CLE));
            droitUtilisateurServices.saveOne(new Droit(constante.ROLE_CREER_PRODUIT, constante.ROLE_CREER_PRODUIT_CLE));
            droitUtilisateurServices.saveOne(new Droit(constante.ROLE_CREER_STOCK, constante.ROLE_CREER_STOCK_CLE));
            droitUtilisateurServices.saveOne(new Droit(constante.ROLE_IMPRIMER_VENTE, constante.ROLE_IMPRIMER_VENTE_CLE));
            droitUtilisateurServices.saveOne(new Droit(constante.ROLE_IMPRIMER_PRODUIT, constante.ROLE_IMPRIMER_PRODUIT_CLE));
            droitUtilisateurServices.saveOne(new Droit(constante.ROLE_IMPRIMER_STOCK, constante.ROLE_IMPRIMER_STOCK_CLE));
            droitUtilisateurServices.saveOne(new Droit(constante.ROLE_MODIFIER_VENTE, constante.ROLE_MODIFIER_VENTE_CLE));
            droitUtilisateurServices.saveOne(new Droit(constante.ROLE_MODIFIER_STOCK, constante.ROLE_MODIFIER_STOCK_CLE));
            droitUtilisateurServices.saveOne(new Droit(constante.ROLE_CREER_PRIX, constante.ROLE_CREER_PRIX_CLE));
            droitUtilisateurServices.saveOne(new Droit(constante.ROLE_CONSULTER_PRIX, constante.ROLE_CONSULTER_PRIX_CLE));
            droitUtilisateurServices.saveOne(new Droit(constante.ROLE_IMPRIMER_PRIX, constante.ROLE_IMPRIMER_PRIX_CLE));
            droitUtilisateurServices.saveOne(new Droit(constante.ROLE_CREER_CLIENT, constante.ROLE_CREER_CLIENT_CLE));
            droitUtilisateurServices.saveOne(new Droit(constante.ROLE_CONSULTER_CLIENT, constante.ROLE_CONSULTER_CLIENT_CLE));
            droitUtilisateurServices.saveOne(new Droit(constante.ROLE_IMPRIMER_CLIENT, constante.ROLE_IMPRIMER_CLIENT_CLE));
            droitUtilisateurServices.saveOne(new Droit(constante.ROLE_MODIFIER_PRODUIT, constante.ROLE_MODIFIER_PRODUIT_CLE));
            
        }

        postes = posteServices.getAll();
        if (postes.isEmpty()) {
            poste.setLibPoste("Invite");
            posteServices.saveOne(poste);
            
            poste = new Poste();
                       
            poste.setLibPoste("Administrateur");
            posteServices.saveOne(poste);
        }

        profilList = profilUtilisateurServices.getAll();
        if (profilList.isEmpty()) {
            profilUtilisateur.setNomProf("Admin");
            profilUtilisateur.setDateCreaProf(new Date());
            profilUtilisateurServices.saveOne(profilUtilisateur);

            droitUtilisateurs2 = droitUtilisateurServices.getNonBy("libDroit", "Tous");

            for (Droit drt : droitUtilisateurs2) {
                possederId.setProfilID(profilUtilisateur.getIdProf());
                possederId.setDroitUtilID(drt.getCodeDroit());
                posseder.setId(possederId);
                posseder.setProfil(profilUtilisateur);
                posseder.setDroitUtilisateur(drt);

                this.possederServices.saveOne(posseder);

                posseder = new Posseder();
                possederId = new PossederId();
            }

            profilUtilisateurServices.saveOne(new Profil("Invite", "Pour les utilisateurs qui n'ont pas de droit", new Date()));

            List<Posseder> poss = possederServices.getBy("profil", profilUtilisateur);
            for (Posseder po : poss) {
                profilUtilisateur.ajouterPosseder(po);
                profilUtilisateurServices.updateOne(profilUtilisateur);
            }

            profil2.setNomProf("All_privilege");
            profil2.setDateCreaProf(new Date());
            profilUtilisateurServices.saveOne(profil2);

            droitTous = droitUtilisateurServices.getOneBy("libDroit", "Tous");

            possederId.setProfilID(profil2.getIdProf());
            possederId.setDroitUtilID(droitTous.getCodeDroit());
            posseder.setId(possederId);
            posseder.setProfil(profil2);
            posseder.setDroitUtilisateur(droitTous);

            this.possederServices.saveOne(posseder);

            posseder = new Posseder();
            possederId = new PossederId();

            List<Posseder> poss2 = possederServices.getBy("profil", profil2);
            for (Posseder po2 : poss2) {
                profil2.ajouterPosseder(po2);
                profilUtilisateurServices.updateOne(profil2);
            }
        }

        users = userServices.getAll();
        if (users.isEmpty()) {
            user.setNom("Admin");
            user.setPrenom("Admin");
            user.setSexe("-");
            user.setLogin("Administrateur");
            user.setPoste(poste);
            user.setMotDePasse(new Sha256Hash(constante.MOT_DE_PASSE_DEFAUT).toHex());
            user.setDateCreation(new Date());
            user.setReinitialiserPswd(true);
            user.setActif(true);
            user.setPhoto(tofProfil);
            user.setProfil(profil2);

            this.userServices.saveOne(user);

        }

        user = new Utilisateur();
        poste = new Poste();
        profilUtilisateur = new Profil();
        profil2 = new Profil();
        droitTous = new Droit();
        droitUtilisateurs2 = new ArrayList<>();

    }

    public void connexionUser() throws IOException {
        UsernamePasswordToken token = new UsernamePasswordToken(userConnexion.getLogin().trim(), userConnexion.getMotDePasse().trim());
        token.setRememberMe(true);

        try {
            SecurityUtils.getSubject().login(token);

            userConnexionTest = userServices.getOneBy("login", userConnexion.getLogin());

            if (userConnexionTest.isReinitialiserPswd()) {
                RequestContext context = RequestContext.getCurrentInstance();
                context.execute("jQuery('#EnregMdp').modal('show', {backdrop: 'static'});");
            } else {
                user1 = userConnexionTest.getLogin();
                userProfil = userConnexionTest.getPhoto();
                userPoste = userConnexionTest.getPoste().getLibPoste();
                SavedRequest savedRequest = WebUtils.getAndClearSavedRequest(Faces.getRequest());
                Faces.redirect(savedRequest != null ? savedRequest.getRequestUrl() : "Accueil.xhtml");
                mtm.logMikiLog4j(ConnexionManagedBean.class.getName(), Level.INFO, "Connexion à l'application");
            }

        } catch (UnknownAccountException uae) {
            //L'utilisateur n'est pas dans le système
            mtm.mikiMessageErrorPerso(uae.getMessage());
        } catch (IncorrectCredentialsException ice) {
            mtm.mikiMessageErrorPerso("Mot de passe incorrect, veuillez réessayer Svp !");
        } catch (LockedAccountException lae) {
            //Compte inactif
            mtm.mikiMessageWarn(lae.getMessage());
            userConnexion = new Utilisateur();
        } catch (AuthenticationException e) {
            e.printStackTrace();
            mtm.mikiMessageError();
            userConnexion = new Utilisateur();
        }

    }

    public void modifMdp() throws IOException {

        userConnexionTest = userServices.getOneBy("login", userConnexion.getLogin());

        if (newMdp.trim().equals(comfMdp.trim())) {
            userConnexionTest.setMotDePasse(new Sha256Hash(newMdp).toHex());
            userConnexionTest.setReinitialiserPswd(false);

            this.userServices.updateOne(userConnexionTest);
            user1 = userConnexionTest.getLogin();
            userProfil = userConnexionTest.getPhoto();
            userPoste = userConnexionTest.getPoste().getLibPoste();
            try {
                SavedRequest savedRequest = WebUtils.getAndClearSavedRequest(Faces.getRequest());
                Faces.redirect(savedRequest != null ? savedRequest.getRequestUrl() : "Accueil.xhtml");
                mtm.logMikiLog4j(ConnexionManagedBean.class.getName(), Level.INFO, "Connexion à l'application");
            } catch (AuthenticationException e) {
                mtm.mikiMessageError();
            }

        } else {
            FacesMessage message5 = new FacesMessage(FacesMessage.SEVERITY_WARN, "Les mots de passe ne sont pas identiques, veuillez resaisir Svp ! ", "");
            FacesContext.getCurrentInstance().addMessage(null, message5);
        }

    }

    public Utilisateur getUserConnexion() {
        return userConnexion;
    }

    public void setUserConnexion(Utilisateur userConnexion) {
        this.userConnexion = userConnexion;
    }

    public Utilisateur getUserConnexionTest() {
        return userConnexionTest;
    }

    public void setUserConnexionTest(Utilisateur userConnexionTest) {
        this.userConnexionTest = userConnexionTest;
    }

    public Utilisateur getUser() {
        return user;
    }

    public void setUser(Utilisateur user) {
        this.user = user;
    }

    public Profil getProfilUtilisateur() {
        return profilUtilisateur;
    }

    public void setProfilUtilisateur(Profil profilUtilisateur) {
        this.profilUtilisateur = profilUtilisateur;
    }

    public Profil getProfil2() {
        return profil2;
    }

    public void setProfil2(Profil profil2) {
        this.profil2 = profil2;
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

    public List<Droit> getDroitUtilisateurs2() {
        return droitUtilisateurs2;
    }

    public void setDroitUtilisateurs2(List<Droit> droitUtilisateurs2) {
        this.droitUtilisateurs2 = droitUtilisateurs2;
    }

    public List<Poste> getPostes() {
        return postes;
    }

    public void setPostes(List<Poste> postes) {
        this.postes = postes;
    }

    public List<Profil> getProfilList() {
        return profilList;
    }

    public void setProfilList(List<Profil> profilList) {
        this.profilList = profilList;
    }

    public List<Utilisateur> getUsers() {
        return users;
    }

    public void setUsers(List<Utilisateur> users) {
        this.users = users;
    }

    public Poste getPoste() {
        return poste;
    }

    public void setPoste(Poste poste) {
        this.poste = poste;
    }

    public String getTofProfil() {
        return tofProfil;
    }

    public void setTofProfil(String tofProfil) {
        this.tofProfil = tofProfil;
    }

    public String getUserProfil() {
        return userProfil;
    }

    public void setUserProfil(String userProfil) {
        this.userProfil = userProfil;
    }

    public String getUser1() {
        return user1;
    }

    public void setUser1(String user1) {
        this.user1 = user1;
    }

    public String getUserPoste() {
        return userPoste;
    }

    public void setUserPoste(String userPoste) {
        this.userPoste = userPoste;
    }

    public Utilisateur getPersUser() {
        return persUser;
    }

    public void setPersUser(Utilisateur persUser) {
        this.persUser = persUser;
    }

    public Droit getDroitTous() {
        return droitTous;
    }

    public void setDroitTous(Droit droitTous) {
        this.droitTous = droitTous;
    }

    public DroitSessionBeanLocal getDroitUtilisateurServices() {
        return droitUtilisateurServices;
    }

    public void setDroitUtilisateurServices(DroitSessionBeanLocal droitUtilisateurServices) {
        this.droitUtilisateurServices = droitUtilisateurServices;
    }

    public UtilisateurSessionBeanLocal getUserServices() {
        return userServices;
    }

    public void setUserServices(UtilisateurSessionBeanLocal userServices) {
        this.userServices = userServices;
    }

    public UtilisateurSessionBeanLocal getUtilisateurServices() {
        return utilisateurServices;
    }

    public void setUtilisateurServices(UtilisateurSessionBeanLocal utilisateurServices) {
        this.utilisateurServices = utilisateurServices;
    }

    public PossederSessionBeanLocal getPossederServices() {
        return possederServices;
    }

    public void setPossederServices(PossederSessionBeanLocal possederServices) {
        this.possederServices = possederServices;
    }

    public ProfilSessionBeanLocal getProfilUtilisateurServices() {
        return profilUtilisateurServices;
    }

    public void setProfilUtilisateurServices(ProfilSessionBeanLocal profilUtilisateurServices) {
        this.profilUtilisateurServices = profilUtilisateurServices;
    }

    public PosteSessionBeanLocal getPosteServices() {
        return posteServices;
    }

    public void setPosteServices(PosteSessionBeanLocal posteServices) {
        this.posteServices = posteServices;
    }

//    public JournalSessionBeanLocal getJournalServices() {
//        return journalServices;
//    }
//
//    public void setJournalServices(JournalSessionBeanLocal journalServices) {
//        this.journalServices = journalServices;
//    }
    public String getNewMdp() {
        return newMdp;
    }

    public void setNewMdp(String newMdp) {
        this.newMdp = newMdp;
    }

    public String getComfMdp() {
        return comfMdp;
    }

    public void setComfMdp(String comfMdp) {
        this.comfMdp = comfMdp;
    }

    public Mtm getMtm() {
        return mtm;
    }

    public void setMtm(Mtm mtm) {
        this.mtm = mtm;
    }
    
    

}
