package com.prodyna.pac.conference.talk;

import com.prodyna.pac.conference.conference.Conference;
import com.prodyna.pac.conference.conference.ConferenceResource;
import com.prodyna.pac.conference.core.test.ArquillianTest;
import com.prodyna.pac.conference.room.RoomResource;
import com.prodyna.pac.conference.speaker.SpeakerResource;
import org.jboss.arquillian.junit.Arquillian;
import org.joda.time.DateTime;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;

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

    @Test
    public void testTalkBeforeConferenceValidation() throws Exception {

        final Conference conference = new Conference();
        conference.setName("PAC");
        conference.setDescription("PAC - Final Presentation");
        conference.setStartDate(new DateTime(2013, 11, 8, 0, 0).withTimeAtStartOfDay().toDate());
        conference.setEndDate(new DateTime(2013, 11, 9, 0, 0).withTimeAtStartOfDay().toDate());
        final long conferenceId = conferenceResource.create(conference);

        final Talk talkBeforeConference = new Talk();
        talkBeforeConference.setName("Markus' presentation");
        talkBeforeConference.setDescription("Markus' presentation....");
        talkBeforeConference.setStartTime(new DateTime(2012, 11, 8, 0, 0).toDate());
        talkBeforeConference.setEndTime(new DateTime(2012, 11, 9, 0, 0).toDate());
        talkBeforeConference.setConference(conferenceResource.read(conferenceId));

    }

}
