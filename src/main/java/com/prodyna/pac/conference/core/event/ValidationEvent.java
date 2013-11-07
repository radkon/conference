package com.prodyna.pac.conference.core.event;

/**
 * @author Markus Konrad <markus.konrad@prodyna.com>
 */
public abstract class ValidationEvent<T> {

    private final T entity;

    protected ValidationEvent(T entity) {
        this.entity = entity;
    }

    public T getEntity() {
        return entity;
    }
}
