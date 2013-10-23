package com.prodyna.pac.conference.entity;

import java.io.Serializable;

public class TalkChangeEvent extends ChangeEvent<Talk> implements Serializable {
    private static final long serialVersionUID = 2075769014736101982L;

    protected TalkChangeEvent(EventType eventType, Talk entity) {
        super(eventType, entity);
    }
}
