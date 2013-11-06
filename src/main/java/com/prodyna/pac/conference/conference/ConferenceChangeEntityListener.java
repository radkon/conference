package com.prodyna.pac.conference.conference;

import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.persistence.PostPersist;
import javax.persistence.PostRemove;
import javax.persistence.PostUpdate;

import static com.prodyna.pac.conference.core.event.ChangeEvent.EventType.*;

public class ConferenceChangeEntityListener {

    @Inject
    private Event<ConferenceChangeEvent> event;

    @PostPersist
    public void postPersist(Conference conference) {
        event.fire(new ConferenceChangeEvent(CREATION, conference));
    }

    @PostUpdate
    public void postUpdate(Conference conference) {
        event.fire(new ConferenceChangeEvent(UPDATE, conference));
    }

    @PostRemove
    public void postRemove(Conference conference) {
        event.fire(new ConferenceChangeEvent(DELETION, conference));
    }

}
