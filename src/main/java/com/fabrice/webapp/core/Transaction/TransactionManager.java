/*
 * Développée par TCHAKPEDEOU Paul, Ing. des Travaux Informatiques,
 * Diplomé de l'IAI - TOGO, Promotion 2015.
 * copyright 2016, Paul Abram Informatics.
 */
package com.fabrice.webapp.core.Transaction;

import javax.naming.InitialContext;
import javax.transaction.UserTransaction;

/**
 *
 * @author PaulAbram
 */
public class TransactionManager {

    public TransactionManager() {
    }

    public static UserTransaction getUserTransaction() {
        UserTransaction utx;
        try {
            InitialContext context = new InitialContext();
            utx = (UserTransaction) context.lookup("java:comp/UserTransaction");
            return utx;
        } catch (Exception e) {
            return null;
        }
    }
}
