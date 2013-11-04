package com.prodyna.pac.conference.talk;

import com.prodyna.pac.conference.core.event.ChangeEvent;

import java.io.Serializable;

public class TalkChangeEvent extends ChangeEvent<Talk> implements Serializable {
    private static final long serialVersionUID = 2075769014736101982L;

    protected TalkChangeEvent(EventType eventType, Talk entity) {
        super(eventType, entity);
    }

    @Override
    public String toString() {
        return "TalkChangeEvent{" +
                "eventType='" + getEventType() + '\'' +
                ", entity='" + getEntity() +
                '}';
    }
}
