package com.prodyna.pac.conference.talk;

import com.prodyna.pac.conference.core.test.ArquillianTest;
import org.jboss.arquillian.junit.Arquillian;
import org.joda.time.DateTime;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Markus Konrad <markus.konrad@prodyna.com>
 */
@RunWith(Arquillian.class)
public class TalkChangeMdbTest extends ArquillianTest {

    @Inject
    TalkResource talkResource;

    @Inject
    TalkChangeLoggerMock talkChangeLoggerMock;

    @Test
    public void testChangeEventPropagation() throws Exception {

        String name = "JavaEE 7";
        String description = "Gives a short introduction to JavaEE 7. Knowledge in JavaEE 6 is assumed.";
        DateTime startTime = new DateTime(2013,1,1,12,0);
        DateTime endTime = startTime.plusHours(1);

        // persist new talk
        Talk talk = new Talk();
        talk.setName(name);
        talk.setDescription(description);
        talk.setConference(null);
        talk.setStartTime(startTime.toDate());
        talk.setEndTime(endTime.toDate());
        talk.setRoom(null);
        long id = talkResource.create(talk);
        assertThat(id).isGreaterThan(0);

        // Expect talkChangeLogger being called...
        Thread.sleep(1000);
        TalkChangeEvent event = talkChangeLoggerMock.getEvent();
        assertThat(event).isNotNull();
        assertThat(event.getEventType()).isEqualTo(TalkChangeEvent.EventType.CREATION);
        assertThat(event.getEntity()).isEqualTo(talk);

        // read back talk
        talk = talkResource.read(id);
        assertThat(talk).isNotNull();
        assertThat(talk.getId()).isEqualTo(id);
        assertThat(talk.getUuid()).isNotNull();
        assertThat(talk.getVersion()).isEqualTo(1L);
        assertThat(talk.getName()).isEqualTo(name);
        assertThat(talk.getDescription()).isEqualTo(description);
        assertThat(talk.getConference()).isNull();
        assertThat(talk.getRoom()).isNull();
        assertThat(talk.getStartTime()).isEqualTo(startTime.toDate());
        assertThat(talk.getEndTime()).isEqualTo(endTime.toDate());

        // read back talk (again)
        talk = talkResource.read(id);
        assertThat(talk).isNotNull();
        assertThat(talk.getId()).isEqualTo(id);
        assertThat(talk.getUuid()).isNotNull();
        assertThat(talk.getVersion()).isEqualTo(1L);
        assertThat(talk.getName()).isEqualTo(name);
        assertThat(talk.getDescription()).isEqualTo(description);
        assertThat(talk.getConference()).isNull();
        assertThat(talk.getRoom()).isNull();
        assertThat(talk.getStartTime()).isEqualTo(startTime.toDate());
        assertThat(talk.getEndTime()).isEqualTo(endTime.toDate());

        // update talk
        description = "JavaEE 7, Glassfish 4, Eclipselink 2.5";
        endTime = endTime.plusHours(1);
        talk.setDescription(description);
        talk.setEndTime(endTime.toDate());
        talk = talkResource.update(id, talk);
        assertThat(talk).isNotNull();
        assertThat(talk.getId()).isEqualTo(id);
        assertThat(talk.getUuid()).isNotNull();
        assertThat(talk.getVersion()).isEqualTo(2L);
        assertThat(talk.getName()).isEqualTo(name);
        assertThat(talk.getDescription()).isEqualTo(description);
        assertThat(talk.getConference()).isNull();
        assertThat(talk.getRoom()).isNull();
        assertThat(talk.getStartTime()).isEqualTo(startTime.toDate());
        assertThat(talk.getEndTime()).isEqualTo(endTime.toDate());

        // Expect talkChangeLogger being called...
        Thread.sleep(1000);
        event = talkChangeLoggerMock.getEvent();
        assertThat(event).isNotNull();
        assertThat(event.getEventType()).isEqualTo(TalkChangeEvent.EventType.UPDATE);
        assertThat(event.getEntity()).isEqualTo(talk);

        // delete talk
        talkResource.delete(id);

        // Expect talkChangeLogger being called...
        Thread.sleep(1000);
        event = talkChangeLoggerMock.getEvent();
        assertThat(event).isNotNull();
        assertThat(event.getEventType()).isEqualTo(TalkChangeEvent.EventType.DELETION);
        assertThat(event.getEntity()).isEqualTo(talk);

        // read back talk
        talk = talkResource.read(id);
        assertThat(talk).isNull();
    }
}
