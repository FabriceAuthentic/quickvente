/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fabrice.quickvente.core.dao;

import java.io.Serializable;
import java.util.List;
import javax.ejb.Local;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author Wilson
 * @param <T>
 * @param <PK>
 */
@Local
public interface BaseDaoBean<T, PK extends Serializable> {

    T getOne(final PK id);

    Long count();

    List<T> getAll();

    List<T> getAll(String sortProperty, boolean sortAsc);

    /**
     *
     * @param <E>
     * @param sortProperty
     * @param sortValue
     * @return une liste de T elts trié par sortProperty like sortValue
     */
    <E> List<T> getAll(String sortProperty, E sortValue);

    /**
     *
     * @param <E>
     * @param sortProperty
     * @param sortValue
     * @return une liste de T elts trié par sortProperty = sortValue
     */
    <E> List<T> getBy(String sortProperty, E sortValue);
    
    /**
     *
     * @param <E>
     * @param sortProperty
     * @param sortValue
     * @return un élément de T elts trié par sortProperty = sortValue
     */
    <E> T getOneBy(String sortProperty, E sortValue);
    
    
    /**
     *
     * @param <E>
     * @param <F>
     * @param sortProperty premier condition
     * @param andSortProperty seconde condition
     * @param sortValue trié par cette valleur
     * @param andSortValue
     * @return une liste de T elts trié par sortProperty = sortValue et andSortProperty
     */
    <E,F> List<T>getBy(String sortProperty,String andSortProperty, E sortValue,F andSortValue);

    /**
     *
     * @param <E>
     * @param sortProperty
     * @param sortValue
     * @return une liste de T elts trié par sortProperty <> sortValue
     */
    public <E> List<T> getNonBy(String sortProperty, E sortValue);

    /**
     * 
     * @param <E>
     * @param sortProperty
     * @param sortValue
     * @param sortAsc
     * @param andsortProperty
     * @return une liste de T elts trié par sortProperty <> sortValue en la triant par ordre decroissant ou croissant du andsortProperty
     */
    public <E> List<T> getNonBy(String sortProperty, E sortValue,String andsortProperty,boolean sortAsc);
    
    /**
     *
     * @param <E>
     * @param sortProperty
     * @param sortValue
     * @param sortAsc
     * @return une liste de T elts trié par sortProperty like sortValue
     */
    <E> List<T> getAll(String sortProperty, E sortValue, boolean sortAsc);

    List<T> getAll(int first, int count, String sortProperty, boolean sortAsc);

    T saveOne(final T t);

    T updateOne(final T t);

    boolean deleteOne(final PK id);

    boolean deleteOne(final T t);

    void deleteAll();

    List<T> executeQuery(Query query);

    int executeUpdate(Query query);

    EntityManager getEntityManager();

    public boolean deleteRealOne(PK id);
    
    boolean deleteRealOne(final T t);
}
