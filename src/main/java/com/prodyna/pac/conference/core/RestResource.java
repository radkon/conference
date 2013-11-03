package com.prodyna.pac.conference.core;

import com.prodyna.pac.conference.entity.EntityBase;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaQuery;
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

    public T read(long id) {
        return entityManager.find(entityClass, id);
    }

    public T update(long id, T entity) {
        return entityManager.merge(entity);
    }

    public void delete(long id) {
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
}
