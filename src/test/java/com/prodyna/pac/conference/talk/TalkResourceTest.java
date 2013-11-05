package com.prodyna.pac.conference.talk;

import com.prodyna.pac.conference.core.test.ArquillianTest;
import com.prodyna.pac.conference.core.test.TalkResourceClient;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.junit.Arquillian;
import org.joda.time.DateTime;
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

            DateTime startTime = new DateTime(2013, 1, 1, 12, 0);
            DateTime endTime = startTime.plusHours(1);

            // persist new talk
            Talk talk = new Talk();
            talk.setName(name);
            talk.setDescription(description);
            talk.setConference(null);
            talk.setStartTime(startTime.toDate());
            talk.setEndTime(endTime.toDate());
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
            assertThat(talk.getStartTime()).isEqualTo(startTime.toDate());
            assertThat(talk.getEndTime()).isEqualTo(endTime.toDate());

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
            assertThat(talk.getStartTime()).isEqualTo(startTime.toDate());
            assertThat(talk.getEndTime()).isEqualTo(endTime.toDate());

            // update talk
            description = "JavaEE 7, Glassfish 4, Eclipselink 2.5";
            endTime = endTime.plusHours(1);
            talk.setDescription(description);
            talk.setEndTime(endTime.toDate());
            talk = client.update(talk);
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

            // delete talk
            client.delete(id);

            // read back talk
            talk = client.read(id);
            assertThat(talk).isNull();

        }
    }

}
