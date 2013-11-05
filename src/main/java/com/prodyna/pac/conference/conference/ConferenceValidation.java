package com.prodyna.pac.conference.conference;

import com.prodyna.pac.conference.talk.Talk;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Markus Konrad <markus.konrad@prodyna.com>
 */
public class ConferenceValidation {

    @Inject
    private EntityManager entityManager;

    public Set<Violation> validate(Talk talk) {
        final Set<Violation> violations = new HashSet<>(3);
        validateSpeakerAvailable(violations);
        validateRoomAvailable(violations);
        validateConferenceAndTalkPeriod(violations, talk);
        return violations;
    }

    private void validateConferenceAndTalkPeriod(final Set<Violation> violations, Talk talk) {
        final Conference conference = talk.getConference();
        if (conference == null) {
            violations.add(Violation.TALK_CONFERENCE_INCOMPATIBILITY);
        } else {
            Date startDate = conference.getStartDate();
            Date endDate = conference.getEndDate();
        }
    }

    /**
     * Checks that no other Talk is taking place in the given Room, during the given period of time.
     *
     * @return <code>true</code> if the Room is available, <code>false</code> otherwise
     * @param violations
     */
    private void validateRoomAvailable(final Set<Violation> violations) {

    }

    private void validateSpeakerAvailable(final Set<Violation> violations) {
        //To change body of created methods use File | Settings | File Templates.
    }

}
