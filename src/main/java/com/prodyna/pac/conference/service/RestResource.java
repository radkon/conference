package com.prodyna.pac.conference.service;

import com.prodyna.pac.conference.datamodel.EntityBase;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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

    @PersistenceContext(unitName = "ConferencePU")
    private EntityManager entityManager;

    private Class<T> entityClass;

    protected RestResource(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    public long create(T entity) {
        entityManager.persist(entity);
        return entity.getId();
    }

    public T update(T entity) {
        return entityManager.merge(entity);
    }

    public T read(long id) {
        return entityManager.find(entityClass, id);
    }

    public void remove(long id) {
        T entity = read(id);
        if (entity != null) {
            entityManager.remove(entity);
        }
    }

    public List<T> findAll() {
        CriteriaQuery<T> cq = entityManager.getCriteriaBuilder().createQuery(entityClass);
        cq.select(cq.from(entityClass));
        return entityManager.createQuery(cq).getResultList();
    }

    protected List<T> findRange(int[] range) {
        CriteriaQuery<T> cq = entityManager.getCriteriaBuilder().createQuery(entityClass);
        cq.select(cq.from(entityClass));
        TypedQuery<T> q = entityManager.createQuery(cq);
        q.setMaxResults(range[1] - range[0]);
        q.setFirstResult(range[0]);
        return q.getResultList();
    }

    public List<T> findRange(Integer from, Integer to) {
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

    public long count() {
        CriteriaQuery<Long> cq = entityManager.getCriteriaBuilder().createQuery(Long.class);
        Root<T> rt = cq.from(entityClass);
        cq.select(entityManager.getCriteriaBuilder().count(rt));
        TypedQuery<Long> q = entityManager.createQuery(cq);
        return q.getSingleResult().longValue();
    }
}
