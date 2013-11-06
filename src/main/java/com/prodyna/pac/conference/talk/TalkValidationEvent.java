package com.prodyna.pac.conference.talk;

import com.prodyna.pac.conference.core.event.ValidationEvent;

/**
 * @author Markus Konrad <markus.konrad@prodyna.com>
 */
public class TalkValidationEvent extends ValidationEvent<Talk> {
    public TalkValidationEvent(Talk validationTarget) {
        super(validationTarget);
    }
}
