package com.prodyna.pac.conference.talk;

import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.persistence.PostPersist;
import javax.persistence.PostRemove;
import javax.persistence.PostUpdate;

import static com.prodyna.pac.conference.core.event.ChangeEvent.EventType.*;

public class TalkEntityListener {

    @Inject
    private Event<TalkChangeEvent> changeEvents;

    @PostPersist
    public void postPersist(Talk talk) {
        changeEvents.fire(new TalkChangeEvent(CREATION, talk));
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
