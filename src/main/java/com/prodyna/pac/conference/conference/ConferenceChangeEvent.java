package com.prodyna.pac.conference.conference;

import com.prodyna.pac.conference.core.event.ChangeEvent;

/**
 * @author Markus Konrad <markus.konrad@prodyna.com>
 */
public class ConferenceChangeEvent extends ChangeEvent<Conference> {
    public ConferenceChangeEvent(EventType eventType, Conference entity) {
        super(eventType, entity);
    }
}
