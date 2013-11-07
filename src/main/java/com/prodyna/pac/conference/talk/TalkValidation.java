package com.prodyna.pac.conference.talk;

import com.prodyna.pac.conference.conference.Conference;
import com.prodyna.pac.conference.conference.ConferenceResource;
import com.prodyna.pac.conference.conference.ConferenceValidationEvent;
import com.prodyna.pac.conference.core.exception.ValidationException;
import com.prodyna.pac.conference.room.Room;
import com.prodyna.pac.conference.room.RoomResource;
import com.prodyna.pac.conference.speaker.Speaker;
import org.joda.time.DateTime;

import javax.enterprise.event.Observes;
import javax.inject.Inject;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Markus Konrad <markus.konrad@prodyna.com>
 */
public class TalkValidation {

    @Inject
    private ConferenceResource conferenceResource;

    @Inject
    private TalkResource talkResource;

    @Inject
    private RoomResource roomResource;

    public void on(@Observes TalkValidationEvent event) {
        validate(event.getEntity());
    }

    public void on(@Observes ConferenceValidationEvent event) {
        validate(event.getEntity());
    }

    private void validate(final Talk talk) {
        final Set<TalkValidationViolation> violations = new HashSet<>();
        validateSpeakerAvailable(violations, talk);
        validateRoomAvailable(violations, talk);
        validateConferenceAndTalkPeriod(violations, talk);
        if (!violations.isEmpty()) {
            final ValidationException validationException = new ValidationException();
            for (final TalkValidationViolation violation : violations) {
                validationException.addMessage(violation.getMessage());
            }
            throw validationException;
        }
    }

    private void validate(final Conference conference) {
        final Set<TalkValidationViolation> violations = new HashSet<>();
        validateConferenceAndTalkPeriod(violations, conference);
        if (!violations.isEmpty()) {
            final ValidationException validationException = new ValidationException();
            for (final TalkValidationViolation violation : violations) {
                validationException.addMessage(violation.getMessage());
            }
            throw validationException;
        }
    }

    private void validateConferenceAndTalkPeriod(final Set<TalkValidationViolation> violations, final Talk talk) {
        final Conference conference = talk.getConference();
        if (conference == null) {
            violations.add(TalkValidationViolation.TALK_CONFERENCE_INCOMPATIBILITY);
        } else {
            final Conference actualConference = conferenceResource.read(conference.getId());
            validateTalkDuringConference(violations, talk, actualConference);
        }
    }

    private void validateConferenceAndTalkPeriod(final Set<TalkValidationViolation> violations, final Conference conference) {
        final List<Talk> talks = talkResource.findByConference(conference.getId());
        for (final Talk talk : talks) {
            validateTalkDuringConference(violations, talk, conference);
        }
    }

    private void validateTalkDuringConference(Set<TalkValidationViolation> violations, Talk talk, Conference conference) {
        if (conference == null) {
            violations.add(TalkValidationViolation.TALK_CONFERENCE_INCOMPATIBILITY);
        } else {
            DateTime conferenceStartDate = new DateTime(conference.getStartDate());
            DateTime conferenceEndDate = new DateTime(conference.getEndDate());
            DateTime talkStartTime = new DateTime(talk.getStartTime());
            DateTime talkEndTime = new DateTime(talk.getEndTime());
            if (conferenceStartDate.isAfter(talkStartTime) || conferenceEndDate.isBefore(talkEndTime)) {
                violations.add(TalkValidationViolation.TALK_CONFERENCE_INCOMPATIBILITY);
            }
        }
    }

    private void validateRoomAvailable(final Set<TalkValidationViolation> violations, final Talk talk) {
        final List<Room> availableRooms = talkResource.findAvailableRoomsByTalkDuration(talk.getStartTime().getTime(), talk.getEndTime().getTime());
        if (!availableRooms.contains(talk.getRoom())) {
            violations.add(TalkValidationViolation.ROOM_UNAVAILABLE);
        }
    }

    private void validateSpeakerAvailable(final Set<TalkValidationViolation> violations, final Talk talk) {
        final List<Speaker> availableSpeakers = talkResource.findAvailableSpeakerByTalkDuration(talk.getStartTime().getTime(), talk.getEndTime().getTime());
        final List<Speaker> speakers = talkResource.findSpeakersByTalk(talk.getId());
        for (final Speaker speaker : speakers) {
            if (!availableSpeakers.contains(speaker)){
                violations.add(TalkValidationViolation.SPEAKER_UNAVAILABLE);
            }
        }
    }

}
