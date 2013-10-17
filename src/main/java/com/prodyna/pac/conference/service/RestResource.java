package com.prodyna.pac.conference.service;

import com.prodyna.pac.conference.datamodel.EntityBase;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * Generic implementation for a RESTful-WebService. A concrete
 * RESTful-WebService should overwrite the Methodes implemented here and
 * annotate them with JAXRS-Annotations.
 *
 * @author Markus Konrad <markus.konrad@prodyna.com>
 */
public abstract class RestResource<T extends EntityBase> {

    private Class<T> entityClass;

    protected RestResource(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    protected abstract EntityManager getEntityManager();

    protected void save(T entity) {
        getEntityManager().merge(entity);
    }

    protected T findById(Long id) {
        return getEntityManager().find(entityClass, id);
    }

    protected void remove(Long id) {
        T entity = findById(id);
        if (entity != null) {
            getEntityManager().remove(entity);
        }
    }

    protected List<T> findAll() {
        CriteriaQuery<T> cq = getEntityManager().getCriteriaBuilder().createQuery(entityClass);
        cq.select(cq.from(entityClass));
        return getEntityManager().createQuery(cq).getResultList();
    }

    protected List<T> findRange(int[] range) {
        CriteriaQuery<T> cq = getEntityManager().getCriteriaBuilder().createQuery(entityClass);
        cq.select(cq.from(entityClass));
        TypedQuery<T> q = getEntityManager().createQuery(cq);
        q.setMaxResults(range[1] - range[0]);
        q.setFirstResult(range[0]);
        return q.getResultList();
    }

    protected List<T> findRange(Integer from, Integer to) {
        final List<T> result;
        if (from == null) {
            if (to == null) {
                result = findAll();
            } else {
                result = findRange(new int[]{0, to});
            }
        } else { // from != null
            if (to == null) {
                result = findRange(new int[]{from, Integer.MAX_VALUE});
            } else {
                result = findRange(new int[]{from, to});
            }
        }
        return result;
    }

    protected int count() {
        CriteriaQuery<Long> cq = getEntityManager().getCriteriaBuilder().createQuery(Long.class);
        Root<T> rt = cq.from(entityClass);
        cq.select(getEntityManager().getCriteriaBuilder().count(rt));
        TypedQuery<Long> q = getEntityManager().createQuery(cq);
        return q.getSingleResult().intValue();
    }
}
