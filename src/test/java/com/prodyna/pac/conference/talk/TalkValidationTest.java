package com.prodyna.pac.conference.talk;

import com.prodyna.pac.conference.conference.Conference;
import com.prodyna.pac.conference.conference.ConferenceResource;
import com.prodyna.pac.conference.core.exception.ValidationException;
import com.prodyna.pac.conference.core.test.ArquillianTest;
import com.prodyna.pac.conference.room.Room;
import com.prodyna.pac.conference.room.RoomResource;
import com.prodyna.pac.conference.speaker.SpeakerResource;
import org.jboss.arquillian.junit.Arquillian;
import org.joda.time.DateTime;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.ejb.EJBException;
import javax.inject.Inject;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Markus Konrad <markus.konrad@prodyna.com>
 */
@RunWith(Arquillian.class)
public class TalkValidationTest extends ArquillianTest{

    @Inject
    RoomResource roomResource;

    @Inject
    SpeakerResource speakerResource;

    @Inject
    ConferenceResource conferenceResource;

    @Inject
    TalkResource talkResource;

    @Inject
    TalkValidation validation;

    @Test(expected = EJBException.class)
    public void testCreateTalkBeforeConferenceValidation() throws Exception {

        final Conference conference = new Conference();
        conference.setName("PAC");
        conference.setDescription("PAC - Final Presentation");
        conference.setStartDate(new DateTime(2013, 11, 8, 0, 0).withTimeAtStartOfDay().toDate());
        conference.setEndDate(new DateTime(2013, 11, 9, 0, 0).withTimeAtStartOfDay().toDate());
        final long conferenceId = conferenceResource.create(conference);

        final Talk talkBeforeConference = new Talk();
        talkBeforeConference.setName("Markus' presentation");
        talkBeforeConference.setDescription("Markus' presentation....");
        talkBeforeConference.setStartTime(new DateTime(2012, 11, 8, 0, 0).getMillis());
        talkBeforeConference.setEndTime(new DateTime(2012, 11, 9, 0, 0).getMillis());
        talkBeforeConference.setConference(conferenceResource.read(conferenceId));
        talkResource.create(talkBeforeConference);
    }

    @Test
    public void testValidateTalkBeforeConferenceValidation() throws Exception {

        final Conference conference = new Conference();
        conference.setName("PAC");
        conference.setDescription("PAC - Final Presentation");
        conference.setStartDate(new DateTime(2013, 11, 8, 0, 0).withTimeAtStartOfDay().toDate());
        conference.setEndDate(new DateTime(2013, 11, 9, 0, 0).withTimeAtStartOfDay().toDate());
        final long conferenceId = conferenceResource.create(conference);

        final Talk talkBeforeConference = new Talk();
        talkBeforeConference.setName("Markus' presentation");
        talkBeforeConference.setDescription("Markus' presentation....");
        talkBeforeConference.setStartTime(new DateTime(2012, 11, 8, 0, 0).getMillis());
        talkBeforeConference.setEndTime(new DateTime(2012, 11, 9, 0, 0).getMillis());
        talkBeforeConference.setConference(conferenceResource.read(conferenceId));
        try {
            validation.on(new TalkValidationEvent(talkBeforeConference));
        } catch (ValidationException e) {
            assertThat(e.getMessages()).containsOnly(TalkValidationViolation.TALK_CONFERENCE_INCOMPATIBILITY
                    .getMessage());
        }
    }

    @Test
    public void testValidateTalkWhenRoomIsUsed() throws Exception {

        final Room room = new Room();
        room.setName("Adria");
        room.setCapacity(20);
        final long roomId = roomResource.create(room);

        final Conference conference = new Conference();
        conference.setName("PAC");
        conference.setDescription("PAC - Final Presentation");
        conference.setStartDate(new DateTime(2013, 11, 8, 0, 0).withTimeAtStartOfDay().toDate());
        conference.setEndDate(new DateTime(2013, 11, 9, 0, 0).withTimeAtStartOfDay().toDate());
        final long conferenceId = conferenceResource.create(conference);

        final Talk talkWhichUsesRoom = new Talk();
        talkWhichUsesRoom.setName("Markus' presentation");
        talkWhichUsesRoom.setDescription("Markus' presentation....");
        talkWhichUsesRoom.setStartTime(new DateTime(2013, 11, 8, 9, 0).getMillis());
        talkWhichUsesRoom.setEndTime(new DateTime(2013, 11, 8, 11, 0).getMillis());
        final Room readRoom = roomResource.read(roomId);
        talkWhichUsesRoom.setRoom(readRoom);
        talkWhichUsesRoom.setConference(conferenceResource.read(conferenceId));
        talkResource.create(talkWhichUsesRoom);

        final Talk newTalk = new Talk();
        newTalk.setName("Markus' presentation");
        newTalk.setDescription("Markus' presentation....");
        newTalk.setStartTime(new DateTime(2013, 11, 8, 10, 0).getMillis());
        newTalk.setEndTime(new DateTime(2013, 11, 8, 12, 0).getMillis());
        newTalk.setRoom(roomResource.read(roomId));
        newTalk.setConference(conferenceResource.read(conferenceId));

        try {
            validation.on(new TalkValidationEvent(newTalk));
        } catch (ValidationException e) {
            assertThat(e.getMessages()).containsOnly(TalkValidationViolation.ROOM_UNAVAILABLE.getMessage());
        }
    }

}
