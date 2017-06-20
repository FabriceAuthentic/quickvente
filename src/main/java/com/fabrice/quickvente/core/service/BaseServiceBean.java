/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fabrice.quickvente.core.service;

import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author Wilson
 * @param <T>
 * @param <PK>
 */
public interface BaseServiceBean<T, PK extends Serializable> {

    T getOne(PK id);

    Long count();

    List<T> getAll();

    List<T> getAll(String sortProperty);

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
     * @param sortAsc
     * @return une liste de T elts trié par sortProperty like sortValue
     */
    <E> List<T> getAll(String sortProperty, E sortValue, boolean sortAsc);

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
     * @return une liste de T elts trié par sortProperty = sortValue
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
     * @return une liste de T elts trié par sortProperty = sortValue et
     * andSortProperty
     */
    <E, F> List<T> getBy(String sortProperty, String andSortProperty, E sortValue, F andSortValue);

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
     * @param andsortProperty
     * @param sortAsc
     * @return une liste de T elts trié par sortProperty <> sortValue en la triant par ordre decroissant ou croissant
     */
    public <E> List<T> getNonBy(String sortProperty, E sortValue,String andsortProperty,boolean sortAsc);

    List<T> getAll(int first, int count, String sortProperty, boolean sortAsc);

    T saveOne(final T t);

    T updateOne(final T t);

    void deleteOne(PK id);

    void deleteOne(final T t);

    void deleteRange(PK[] pks);

    void deleteRange(Collection<T> ts);

    void deleteRange(Iterator<T> ts);

    Collection<T> saveRange(Collection<T> ts);

    Collection<T> saveRange(Iterator<T> ts);

    Collection<T> updateRange(Collection<T> ts);

    Collection<T> updateRange(Iterator<T> ts);

    void deleteAll();

    boolean exist(final PK pk);

    boolean deleteRealOne(PK id);

}
