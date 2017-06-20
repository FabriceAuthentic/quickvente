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
@FacesValidator("miki.methodes.templates.mikiTelephoneValidator")
public class mikiTelephoneValidator implements Validator{
    private Pattern p = Pattern.compile("^[+228]?[9|2][\\d]{7,}$");
    
    @Override
    public void validate(FacesContext facesContext, UIComponent component, Object value) throws ValidatorException{
        Matcher m = p.matcher((String) value);
        
        if(!m.matches()){
            throw new ValidatorException(new FacesMessage( 
					FacesMessage.SEVERITY_ERROR, "Entrée non valide :", 
					"numéro de télephone du client non valide")); 
        }
    }
    
}
