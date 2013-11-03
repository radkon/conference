package com.prodyna.pac.conference.service;

import com.prodyna.pac.conference.entity.Talk;
import com.prodyna.pac.conference.entity.TalkChangeEvent;
import com.prodyna.pac.conference.test.ArquillianTest;
import org.jboss.arquillian.junit.Arquillian;
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
        int startTime = 1200;
        int endTime = 1300;

        // persist new talk
        Talk talk = new Talk();
        talk.setName(name);
        talk.setDescription(description);
        talk.setConference(null);
        talk.setStartTime(startTime);
        talk.setEndTime(endTime);
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
        assertThat(talk.getStartTime()).isEqualTo(startTime);
        assertThat(talk.getEndTime()).isEqualTo(endTime);

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
        assertThat(talk.getStartTime()).isEqualTo(startTime);
        assertThat(talk.getEndTime()).isEqualTo(endTime);

        // update talk
        description = "JavaEE 7, Glassfish 4, Eclipselink 2.5";
        endTime = 1400;
        talk.setDescription(description);
        talk.setEndTime(endTime);
        talk = talkResource.update(talk);
        assertThat(talk).isNotNull();
        assertThat(talk.getId()).isEqualTo(id);
        assertThat(talk.getUuid()).isNotNull();
        assertThat(talk.getVersion()).isEqualTo(2L);
        assertThat(talk.getName()).isEqualTo(name);
        assertThat(talk.getDescription()).isEqualTo(description);
        assertThat(talk.getConference()).isNull();
        assertThat(talk.getRoom()).isNull();
        assertThat(talk.getStartTime()).isEqualTo(startTime);
        assertThat(talk.getEndTime()).isEqualTo(endTime);

        // Expect talkChangeLogger being called...
        Thread.sleep(1000);
        event = talkChangeLoggerMock.getEvent();
        assertThat(event).isNotNull();
        assertThat(event.getEventType()).isEqualTo(TalkChangeEvent.EventType.UPDATE);
        assertThat(event.getEntity()).isEqualTo(talk);

        // delete talk
        talkResource.remove(id);

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
