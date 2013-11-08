package com.prodyna.pac.conference.talk;

import com.prodyna.pac.conference.conference.Conference;
import com.prodyna.pac.conference.conference.ConferenceResource;
import com.prodyna.pac.conference.core.test.ArquillianTest;
import com.prodyna.pac.conference.room.Room;
import com.prodyna.pac.conference.room.RoomResource;
import org.jboss.arquillian.junit.Arquillian;
import org.joda.time.DateTime;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(Arquillian.class)
public class TalkResourceTest extends ArquillianTest {

    @Inject
    RoomResource roomResource;

    @Inject
    ConferenceResource conferenceResource;

    @Inject
    TalkResource talkResource;

    @Test
    public void testCheckRoomAvailableByTalkDuration() throws Exception {

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

        final Talk talk = new Talk();
        talk.setName("Markus' presentation");
        talk.setDescription("Markus' presentation....");
        talk.setStartTime(new DateTime(2013, 11, 8, 9, 0).getMillis());
        talk.setEndTime(new DateTime(2013, 11, 8, 11, 0).getMillis());
        final Room readRoom = roomResource.read(roomId);
        talk.setRoom(readRoom);
        talk.setConference(conferenceResource.read(conferenceId));
        final Long numberOfParallelTasksInRoom = talkResource.checkRoomAvailableByTalkDuration(roomId, talk.getStartTime(),
                talk.getEndTime());
        assertThat(numberOfParallelTasksInRoom).isEqualTo(0);

    }

}
