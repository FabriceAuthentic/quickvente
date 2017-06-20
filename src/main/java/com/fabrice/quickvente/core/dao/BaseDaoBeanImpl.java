/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fabrice.quickvente.core.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Wilson
 * @param <T>
 * @param <PK>
 */
public class BaseDaoBeanImpl<T extends Object, PK extends Serializable> implements BaseDaoBean<T, PK> {

    @PersistenceContext(unitName = "QuickVente_Unit")
    protected EntityManager em;
    protected Class<T> type;

    public BaseDaoBeanImpl() {
        ParameterizedType genericSuperclass = (ParameterizedType) getClass().
                getGenericSuperclass();
        this.type = (Class<T>) genericSuperclass.getActualTypeArguments()[0];
    }

    public BaseDaoBeanImpl(Class<T> type) {
        this.type = type;
    }
    
    

    /**
     *
     * @param id
     * @return
     */
    @Override
    public T getOne(final PK id) {
        return (T) em.find(type, id);
    }

    /**
     *
     * @return
     */
    @Override
    public Long count() {
        return (Long) em.createQuery("SELECT COUNT(t) FROM "
                + type.getSimpleName() + " t").getSingleResult();
    }

    /**
     *
     * @return
     */
    @Override
    public List<T> getAll() {
        return (List<T>) em.createQuery("SELECT t FROM "
                + type.getSimpleName() + " t").getResultList();
    }

    /**
     *
     * @param sortProperty
     * @param sortAsc
     * @return
     */
    @Override
    public List<T> getAll(String sortProperty, boolean sortAsc) {
        return (List<T>) em.createQuery("SELECT t FROM " + type.getSimpleName()
                + " t ORDER BY t." + sortProperty
                + ((sortAsc) ? " ASC" : " DESC")).getResultList();
    }

    @Override
    public <E> List<T> getAll(String sortProperty, E sortValue) {
        try {
            String jpql = "SELECT t FROM " + type.getSimpleName()
                    + " t WHERE t." + sortProperty + " LIKE :d ";
            Query query = em.createQuery(jpql);
            query.setParameter("d", "%" + sortValue + "%");
            return query.getResultList();
        } catch (NoResultException exception) {
            exception.printStackTrace();
            return null;
        }
    }

    @Override
    public <E> List<T> getAll(String sortProperty, E sortValue, boolean sortAsc) {
        try {
            String jpql = "SELECT t FROM " + type.getSimpleName()
                    + " t WHERE t." + sortProperty + " LIKE :d  "
                    + " ORDER BY t." + sortProperty + ((sortAsc) ? " ASC" : " DESC");
            Query query = em.createQuery(jpql);
            query.setParameter("d", "%" + sortValue + "%");
            return query.getResultList();
        } catch (NoResultException exception) {
            exception.printStackTrace();
            return null;
        }
    }

    @Override
    public <E> List<T> getBy(String sortProperty, E sortValue) {
        try {
            String jpql = "SELECT t FROM " + type.getSimpleName()
                    + " t WHERE t." + sortProperty + " =:d "
                    + " ORDER BY t." + sortProperty + " ASC";
            Query query = em.createQuery(jpql);
            query.setParameter("d", sortValue);
            return query.getResultList();
        } catch (NoResultException exception) {
            exception.printStackTrace();
            return null;
        }
    }
    @Override
    public <E> T getOneBy(String sortProperty, E sortValue) {
        try {
            String jpql = "SELECT t FROM " + type.getSimpleName()
                    + " t WHERE t." + sortProperty + " =:d "
                    + " ORDER BY t." + sortProperty + " ASC";
            Query query = em.createQuery(jpql);
            query.setParameter("d", sortValue);
            return(T) query.getSingleResult();
        } catch (NoResultException exception) {
//            exception.printStackTrace();
            return null;
        }
    }

    @Override
    public <E, F> List<T> getBy(String sortProperty, String andSortProperty, E sortValue, F andSortValue) {
        try {
            String jpql = "SELECT t FROM " + type.getSimpleName()
                    + " t WHERE t." + sortProperty + " =:e "
                    + "AND t." + andSortProperty + " =:f "
                    + " ORDER BY t." + sortProperty + " ASC";
            Query query = em.createQuery(jpql);
            query.setParameter("e", sortValue);
            query.setParameter("f", andSortValue);
            return query.getResultList();
        } catch (NoResultException exception) {
            exception.printStackTrace();
            return null;
        }
    }

    @Override
    public <E> List<T> getNonBy(String sortProperty, E sortValue) {
        try {
            String jpql = "SELECT t FROM " + type.getSimpleName()
                    + " t WHERE t." + sortProperty + " <>:d ";
            Query query = em.createQuery(jpql);
            query.setParameter("d", sortValue);
            return query.getResultList();
        } catch (NoResultException exception) {
            exception.printStackTrace();
            return null;
        }
    }
    
    @Override
    public <E> List<T> getNonBy(String sortProperty, E sortValue,String andsortProperty,boolean sortAsc) {
        try {
            String jpql = "SELECT t FROM " + type.getSimpleName()
                    + " t WHERE t." + sortProperty + " <>:d "
                    + " ORDER BY t." + andsortProperty + ((sortAsc) ? " ASC" : " DESC");
            Query query = em.createQuery(jpql);
            query.setParameter("d", sortValue);
            return query.getResultList();
        } catch (NoResultException exception) {
            exception.printStackTrace();
            return null;
        }
    }

    @Override
    public List<T> getAll(int first, int count, String sortProperty, boolean sortAsc) {
        return (List<T>) em.createQuery("SELECT t FROM " + type.getSimpleName()
                + " t ORDER BY t." + sortProperty
                + ((sortAsc) ? " ASC" : " DESC")).
                setFirstResult(first).setMaxResults(count).getResultList();
    }

    @Override
    public T saveOne(final T t) {
        em.persist(t);
        return t;
    }

    @Override
    public T updateOne(final T t) {
        return (T) em.merge(t);
    }

   @Override
    public boolean deleteOne(final PK id) {
        boolean eff = false;
        T t = em.find(type, id);
        if (t == null) {
            eff = false;
        } else {
            em.remove(t);
            eff = true;
        }
        return eff;
    }

    @Override
    public boolean deleteRealOne(final PK id) {
        try {
            T t = em.find(type, id);
            this.em.remove(t);
            return true;

        } catch (Exception e) {
            return false;
        }
    }

    /**
     *
     * @param t
     * @return
     */
    @Override
    public boolean deleteOne(final T t) {
        em.remove(t);
        return true;
    }

    /**
     *
     */
    @Override
    public void deleteAll() {
        em.createQuery("DELETE FROM " + type.getSimpleName()).executeUpdate();
    }

    /**
     *
     * @param query
     * @return
     */
    @Override
    public List<T> executeQuery(Query query) {
        return (List<T>) query.getResultList();
    }

    /**
     *
     * @param query
     * @return
     */
    @Override
    public int executeUpdate(Query query) {
        return query.executeUpdate();
    }

    /**
     *
     * @return
     */
    @Override
    public EntityManager getEntityManager() {
        return this.em;
    }

    @Override
    public boolean deleteRealOne(T t) {
        em.remove(t);
        return true;
    }

    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    public Class<T> getType() {
        return type;
    }

    public void setType(Class<T> type) {
        this.type = type;
    }

}
