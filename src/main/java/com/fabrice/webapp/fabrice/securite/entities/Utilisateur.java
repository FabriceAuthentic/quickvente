/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fabrice.webapp.fabrice.securite.entities;


import com.fabrice.webapp.core.entities.BaseEntite;
import java.util.Date;
import java.util.Objects;
import javax.persistence.*;

/**
 *
 * @author Mikel
 */
@Entity
@Table(name = "UTILISATEURS")
public class Utilisateur extends BaseEntite{

    @Id
    @SequenceGenerator(name = "utilisateurSeq", sequenceName = "UTILISATEUR_SEQ", allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "utilisateurSeq")
    @Column(name = "ID",nullable = false)
    private Long id;
    
    @Column(name = "LOGIN", nullable = false, unique = true)
    private String login;
    
    @Column(name = "MOT_DE_PASSE",nullable = false)
    private String motDePasse;
    
    @Column(name = "ACTIF", nullable = false)
    private boolean actif;
    
    @Column(name = "NOM", nullable = true)
    private String nom;
    
    @Column(name = "PRENOM", nullable = true)
    private String prenom;
    
    @Column(name = "SEXE", nullable = true)
    private String sexe;
    
    @Column(name = "EMAIL", nullable = true)
    private String adresseEmail;
    
//    @Column(name = "DATE_EXPIRATION")
//    @Temporal(TemporalType.DATE)
//    private Date dateExpiration;
//    @Column(name = "NIVEAU_SECURITE")
//    private Integer niveauSecurite = 1;
//    @Column(name = "DUREE_MOT_DE_PASSE")
//    private Integer dureeMotDePasse = 30;
//    @Column(name = "CYCLE_MOT_DE_PASSE")
//    private Integer cycleMotDePasse = 3;
    
    @Column(name = "REINIT_MOT_DE_PASSE")
    private boolean reinitialiserPswd = false;
    
    @Column(name = "PHOTO", nullable = true)
    private String photo;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DATE_CREATION", nullable = true)
    private Date dateCreation;
    
    @Column(name = "CONTACT", nullable = true)
    private String contact;
    
    @Column(name = "ADRESSE", nullable = true)
    private String adresse;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Poste_ID", nullable = false)
    private Poste poste;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Profil_ID", nullable = false)
    private Profil profil;
    
    public Utilisateur() {
    }

    public Utilisateur(String login, String motDePasse, boolean actif, String nom, String prenom, String sexe, String adresseEmail, String photo, Date dateCreation, String contact, String adresse, Poste poste, Profil profil) {
        this.login = login;
        this.motDePasse = motDePasse;
        this.actif = actif;
        this.nom = nom;
        this.prenom = prenom;
        this.sexe = sexe;
        this.adresseEmail = adresseEmail;
        this.photo = photo;
        this.dateCreation = dateCreation;
        this.contact = contact;
        this.adresse = adresse;
        this.poste = poste;
        this.profil = profil;
    }    

    public Date getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }   

    public Poste getPoste() {
        return poste;
    }

    public void setPoste(Poste poste) {
        this.poste = poste;
    }

    public Profil getProfil() {
        return profil;
    }

    public void setProfil(Profil profil) {
        this.profil = profil;
    }    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    public boolean isActif() {
        return actif;
    }

    public void setActif(boolean actif) {
        this.actif = actif;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getNomComplet() {
        return nom + " " + prenom;
    }

    public String getAdresseEmail() {
        return adresseEmail;
    }

    public void setAdresseEmail(String adresseEmail) {
        this.adresseEmail = adresseEmail;
    }

//    public int getCycleMotDePasse() {
//        return cycleMotDePasse;
//    }
//
//    public void setCycleMotDePasse(int cycleMotDePasse) {
//        this.cycleMotDePasse = cycleMotDePasse;
//    }
//
//    public Date getDateExpiration() {
//        return dateExpiration;
//    }
//
//    public void setDateExpiration(Date dateExpiration) {
//        this.dateExpiration = dateExpiration;
//    }
//
//    public int getDureeMotDePasse() {
//        return dureeMotDePasse;
//    }
//
//    public void setDureeMotDePasse(int dureeMotDePasse) {
//        this.dureeMotDePasse = dureeMotDePasse;
//    }
//
//    public int getNiveauSecurite() {
//        return niveauSecurite;
//    }
//
//    public void setNiveauSecurite(int niveauSecurite) {
//        this.niveauSecurite = niveauSecurite;
//    }

    public String getSexe() {
        return sexe;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }
    
    

    public boolean isReinitialiserPswd() {
        return reinitialiserPswd;
    }

    public void setReinitialiserPswd(boolean reinitialiserPswd) {
        this.reinitialiserPswd = reinitialiserPswd;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Utilisateur other = (Utilisateur) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
    

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 23 * hash + (this.id != null ? this.id.hashCode() : 0);
        return hash;
    }

    @Override
    public String toString() {
        return "Utilisateur{" + "id=" + id + ", login=" + login + ", motDePasse=" + motDePasse + ", actif=" + actif + ", nom=" + nom + ", prenom=" + prenom + ", sexe=" + sexe + ", adresseEmail=" + adresseEmail + ", reinitialiserPswd=" + reinitialiserPswd + ", photo=" + photo + ", dateCreation=" + dateCreation + ", contact=" + contact + ", adresse=" + adresse + ", poste=" + poste + ", profil=" + profil + '}';
    }

    
    

    

    @Override
    public String creerChaineModification(Object o) {
        Utilisateur ancien = (Utilisateur) o;
        if (ancien == null) {
            ancien = new Utilisateur();
        }
        String str = "(";
        if (this.login != null) {
            if (!this.login.equals(ancien.login)) {
                str += "login: " + this.login;
            }
        }
        if (this.nom != null) {
            if (!this.nom.equals(ancien.nom)) {
                str += ", nom: " + this.nom;
            }
        }
        if (this.prenom != null) {
            if (!this.prenom.equals(ancien.prenom)) {
                str += ", prenom: " + this.prenom;
            }
        }
        
        if (this.sexe != null) {
            if (!this.sexe.equals(ancien.sexe)) {
                str += ", sexe: " + this.sexe;
            }
        }
        if (this.motDePasse != null) {
            if (!this.motDePasse.equals(ancien.motDePasse)) {
                str += ", mot de passe modifié";
            }
        }
        if (this.actif != ancien.actif) {
            str += ", actif: " + this.actif;
        }
        if (this.adresseEmail != null) {
            if (!this.adresseEmail.equals(ancien.adresseEmail)) {
                str += ", email: " + this.adresseEmail;
            }
        }
//        if (this.dateExpiration != null) {
//            if (!this.dateExpiration.equals(ancien.dateExpiration)) {
//                str += ", expiration: " + this.dateExpiration;
//            }
//        } else {
//            if (ancien.dateExpiration != null) {
//                str += ", expiration: non défini";
//            }
//        }
//        if (this.niveauSecurite != ancien.niveauSecurite) {
//            str += ", niveau: " + this.niveauSecurite;
//        }
//        if (this.dureeMotDePasse != ancien.dureeMotDePasse) {
//            str += ", duree: " + this.dureeMotDePasse;
//        }
//        if (this.cycleMotDePasse != ancien.cycleMotDePasse) {
//            str += ", cycle: " + this.cycleMotDePasse;
//        }
        if (this.reinitialiserPswd != ancien.reinitialiserPswd) {
            str += ", reinitialiser: " + this.reinitialiserPswd;
        }

        str += ")";
        setChaineModification(str);
        return str;
    }

    @Override
    public Object clone() {
        Utilisateur clone = null;
        try {
            clone = (Utilisateur) super.clone();
        } catch (CloneNotSupportedException cnse) {
            cnse.printStackTrace(System.err);
        }
        // on renvoie le clone
        return clone;
    }
}

