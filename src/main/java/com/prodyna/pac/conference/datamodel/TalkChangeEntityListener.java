package com.prodyna.pac.conference.datamodel;

import com.prodyna.pac.conference.service.TalkChangeEvent;

import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.persistence.PostPersist;
import javax.persistence.PostRemove;
import javax.persistence.PostUpdate;
import java.util.logging.Logger;

public class TalkChangeEntityListener {

    private final Logger logger = Logger.getLogger(getClass().getName());

    @Inject
    private Event<TalkChangeEvent> event;

    @PostPersist
    public void postPersist(Talk talk) {
        logger.info("POST PERSIST: " + talk);
        event.fire(new TalkChangeEvent());
    }

    @PostUpdate
    public void postUpdate(Talk talk) {
        logger.info("POST UPDATE: " + talk);
        event.fire(new TalkChangeEvent());
    }

    @PostRemove
    public void postRemove(Talk talk) {
        logger.info("POST DELETE: " + talk);
        event.fire(new TalkChangeEvent());
    }

}
