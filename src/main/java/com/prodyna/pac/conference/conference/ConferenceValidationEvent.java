package com.prodyna.pac.conference.conference;

import com.prodyna.pac.conference.core.event.ValidationEvent;

/**
 * @author Markus Konrad <markus.konrad@prodyna.com>
 */
public class ConferenceValidationEvent extends ValidationEvent<Conference> {
    public ConferenceValidationEvent(Conference entity) {
        super(entity);
    }
}
