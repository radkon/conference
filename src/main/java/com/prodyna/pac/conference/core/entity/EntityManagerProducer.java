package com.prodyna.pac.conference.core.entity;

import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Exposes the EntityManager to CDI.
 *
 * @author Markus Konrad <markus.konrad@prodyna.com>
 */
public class EntityManagerProducer {

    @Produces
    @PersistenceContext(unitName = "ConferencePU")
    private EntityManager entityManager;

}
