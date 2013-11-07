package com.prodyna.pac.conference.conference;

import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

public class ConferenceEntityListener {

    @Inject
    private Event<ConferenceValidationEvent> event;

    @PrePersist
    public void prePersist(Conference conference) {
        event.fire(new ConferenceValidationEvent(conference));
    }

    @PreUpdate
    public void preUpdate(Conference conference) {
        event.fire(new ConferenceValidationEvent(conference));
    }

}
