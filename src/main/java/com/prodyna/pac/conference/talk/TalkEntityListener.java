package com.prodyna.pac.conference.talk;

import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.persistence.*;

import static com.prodyna.pac.conference.core.event.ChangeEvent.EventType.*;

public class TalkEntityListener {

    @Inject
    private Event<TalkValidationEvent> validationEvents;

    @Inject
    private Event<TalkChangeEvent> changeEvents;

    @PrePersist
    public void prePersist(Talk talk) {
        validationEvents.fire(new TalkValidationEvent(talk));
    }

    @PostPersist
    public void postPersist(Talk talk) {
        changeEvents.fire(new TalkChangeEvent(CREATION, talk));
    }

    @PreUpdate
    public void preUpdate(Talk talk) {
        validationEvents.fire(new TalkValidationEvent(talk));
    }

    @PostUpdate
    public void postUpdate(Talk talk) {
        changeEvents.fire(new TalkChangeEvent(UPDATE, talk));
    }

    @PostRemove
    public void postRemove(Talk talk) {
        changeEvents.fire(new TalkChangeEvent(DELETION, talk));
    }

}
