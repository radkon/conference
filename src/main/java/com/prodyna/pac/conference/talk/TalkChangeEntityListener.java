package com.prodyna.pac.conference.talk;

import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.persistence.PostPersist;
import javax.persistence.PostRemove;
import javax.persistence.PostUpdate;

import static com.prodyna.pac.conference.core.event.ChangeEvent.EventType.*;

public class TalkChangeEntityListener {

    @Inject
    private Event<TalkChangeEvent> event;

    @PostPersist
    public void postPersist(Talk talk) {
        event.fire(new TalkChangeEvent(CREATION, talk));
    }

    @PostUpdate
    public void postUpdate(Talk talk) {
        event.fire(new TalkChangeEvent(UPDATE, talk));
    }

    @PostRemove
    public void postRemove(Talk talk) {
        event.fire(new TalkChangeEvent(DELETION, talk));
    }

}
