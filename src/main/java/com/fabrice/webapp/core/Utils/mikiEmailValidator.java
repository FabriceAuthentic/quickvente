/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fabrice.webapp.core.Utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 *
 * @author Mikel
 */
@FacesValidator("miki.methodes.templates.mikiEmailValidator")
public class mikiEmailValidator implements Validator{
    
    private Pattern p = Pattern.compile("^[a-z0-9._-]+@[a-z0-9._-]{2,}\\.[a-z]{2,4}$");
    
    @Override
    public void validate(FacesContext facesContext, UIComponent component, Object value) throws ValidatorException{
        Matcher m = p.matcher((String) value);
        
        if(!m.matches()){
            throw new ValidatorException(new FacesMessage( 
					FacesMessage.SEVERITY_ERROR, "Entrée non valide :", 
					"Email invalide")); 
        }
    }
    
}
