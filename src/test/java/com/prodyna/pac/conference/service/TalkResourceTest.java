package com.prodyna.pac.conference.service;

import com.prodyna.pac.conference.entity.Talk;
import com.prodyna.pac.conference.test.ArquillianTest;
import com.prodyna.pac.conference.test.TalkResourceClient;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.junit.Arquillian;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.net.URI;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(Arquillian.class)
public class TalkResourceTest extends ArquillianTest {

    @RunAsClient
    @Test
    public void testCRUD() throws Exception {

        try (TalkResourceClient client = new TalkResourceClient(new URI("http://localhost:8080/conference/rest/talk"))) {

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
            long id = client.create(talk);
            assertThat(id).isGreaterThan(0);

            // read back talk
            talk = client.read(id);
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
            talk = client.read(id);
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
            talk = client.update(talk);
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

            // delete talk
            client.delete(id);

            // read back talk
            talk = client.read(id);
            assertThat(talk).isNull();

        }
    }

}
