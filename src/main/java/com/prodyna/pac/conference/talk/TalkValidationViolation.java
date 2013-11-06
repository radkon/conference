package com.prodyna.pac.conference.talk;

/**
 * Represents a validation violation. Every literal holds a message messageTemplate, which describes the actual violation in human-readable form.
 *
 * @author Markus Konrad <markus.konrad@prodyna.com>
 */
public enum TalkValidationViolation {

    SPEAKER_UNAVAILABLE("At least on the of the assigned speakers is unavailable during the given period of time."),
    ROOM_UNAVAILABLE("The assigned room is unavailable during the given period of time."),
    TALK_CONFERENCE_INCOMPATIBILITY("At least one talk is not taking place during the coference's duration.");

    private String message;

    private TalkValidationViolation(final String message){
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
