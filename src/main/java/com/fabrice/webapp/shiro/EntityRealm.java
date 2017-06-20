/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fabrice.webapp.shiro;

/**
 *
 * @author MEDIA
 */

import com.fabrice.webapp.fabrice.securite.Dao.IPossederDAO;
import com.fabrice.webapp.fabrice.securite.Dao.IUtilisateurDAO;
import com.fabrice.webapp.fabrice.securite.entities.Posseder;
import com.fabrice.webapp.fabrice.securite.entities.Utilisateur;
import java.util.ArrayList;
import java.util.List;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.CredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;

public class EntityRealm extends AuthorizingRealm {

    protected IUtilisateurDAO userDAO;
    protected static Utilisateur user;
    protected IPossederDAO possederDAO;

    public EntityRealm() throws NamingException {
        System.out.println("enter entity realm");
        this.setName("entityRealm");
        CredentialsMatcher credentialsMatcher = new HashedCredentialsMatcher("SHA-256");
        this.setCredentialsMatcher(credentialsMatcher);
        InitialContext context = new InitialContext();
        this.userDAO = (IUtilisateurDAO) context.lookup("java:global/QuickVente/UtilisateurDAO");
        System.out.println("out entity realm");
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {

        final UsernamePasswordToken token = (UsernamePasswordToken) authcToken;

        SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo();

        user = userDAO.getOneBy("login", token.getUsername());

        if (user != null) {
            
            //Control de l'activation du compte
            if (!user.isActif()) {
                throw new LockedAccountException("Désolé votre compte est inactif, veuillez contacter l'administrateur Svp");
            }                      
            
            //Connexion
            simpleAuthenticationInfo = new SimpleAuthenticationInfo(user.getLogin(), user.getMotDePasse(), getName());
            return simpleAuthenticationInfo;
        }else{
            throw new UnknownAccountException("L'utilisateur ne se trouve pas dans le système, veuillez réessayer Svp !");
        }       
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        // System.out.println("realm; doGetAuthorizationInfo: ");
        SimpleAuthorizationInfo info = null;

        try {
            String userId = (String) principals.fromRealm(this.getName()).iterator().next();
            user = userDAO.getOneBy("login", userId);
            if (user != null) {
                /*
                 * On recupère les profils de l'utilisateur
                 * (role == profil ) : Dans notre application un profil correspond à un role dans shiro 
                 */
                info = new SimpleAuthorizationInfo();
                String nomGroupeUtil = user.getProfil().getNomProf();
                info.addRole(nomGroupeUtil);
                /*
                 * On recupère la liste des menus auquel à droit l'utilisateur
                 * (role == profil ) : Dans notre application un profil correspond à un role dans shiro 
                 */
                final List<String> perm = new ArrayList<>();
                for (Posseder po : user.getProfil().getPosseders()) {
                    perm.add(po.getDroitUtilisateur().getCleDroit());
                }
                info.addStringPermissions(perm);

            }
        } catch (Exception e) {

        }
        return info;
    }

    public static Utilisateur getUser() {
        Subject currentUser = SecurityUtils.getSubject();
        if (currentUser.isAuthenticated()) {
            return user;
        }
        return null;
    }

    public static Subject getSubject() {
        return SecurityUtils.getSubject();

    }

    @Override
    public void clearCachedAuthorizationInfo(PrincipalCollection principals) {
        super.clearCachedAuthorizationInfo(principals);
    }

}
