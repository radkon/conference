package com.prodyna.pac.conference.core.event;

import com.prodyna.pac.conference.core.entity.EntityBase;

import java.io.Serializable;

public abstract class ChangeEvent<T extends EntityBase> implements Serializable {

    private static final long serialVersionUID = 3622715836986061023L;
    private EventType eventType;
    private final T entity;

    protected ChangeEvent(EventType eventType, T entity) {
        this.eventType = eventType;
        this.entity = entity;
    }

    public EventType getEventType() {
        return eventType;
    }

    public T getEntity() {
        return entity;
    }

    public static enum EventType {
        CREATION,
        UPDATE,
        DELETION
    }
}
