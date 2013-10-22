package com.prodyna.pac.conference.service;

import com.prodyna.pac.conference.datamodel.Speaker;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.Filters;
import org.jboss.shrinkwrap.api.GenericArchive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.importer.ExplodedImporter;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.net.URI;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(Arquillian.class)
public class SpeakerResourceTest {

    private static final String WEBAPP_SRC = "src/main/webapp";

    @Deployment
    public static Archive<?> createDeployment() {
        WebArchive war = ShrinkWrap.create(WebArchive.class, "conference.war").addPackages(true, "com.prodyna.pac.conference");
        war.addAsWebInfResource("META-INF/persistence.xml", "classes/META-INF/persistence.xml");
        war.merge(ShrinkWrap.create(GenericArchive.class).as(ExplodedImporter.class)
                .importDirectory(WEBAPP_SRC).as(GenericArchive.class),
                "/", Filters.includeAll());
        return war;
    }

    @RunAsClient
    @Test
    public void testCRUD() throws Exception {

        try (SpeakerResourceClient client = new SpeakerResourceClient(new URI("http://localhost:8080/conference/rest/speaker"))) {

            // persist new speaker
            String name = "Markus Konrad";
            String description = "JavaEE 6, JBoss 7.1.1, Hibernate 4.0.1";
            Speaker speaker = new Speaker(name, description);
            long id = client.create(speaker);
            assertThat(id).isGreaterThan(0);

            // read back speaker
            speaker = client.read(id);
            assertThat(speaker).isNotNull();
            assertThat(speaker.getId()).isEqualTo(id);
            assertThat(speaker.getUuid()).isNotNull();
            assertThat(speaker.getVersion()).isEqualTo(1L);
            assertThat(speaker.getName()).isEqualTo(name);
            assertThat(speaker.getDescription()).isEqualTo(description);

            // read back speaker (again)
            speaker = client.read(id);
            assertThat(speaker).isNotNull();
            assertThat(speaker.getId()).isEqualTo(id);
            assertThat(speaker.getUuid()).isNotNull();
            assertThat(speaker.getVersion()).isEqualTo(1L);
            assertThat(speaker.getName()).isEqualTo(name);
            assertThat(speaker.getDescription()).isEqualTo(description);

            // update speaker
            description = "JavaEE 7, Glassfish 4, Eclipselink 2.5";
            speaker.setDescription(description);
            speaker = client.update(speaker);
            assertThat(speaker).isNotNull();
            assertThat(speaker.getId()).isEqualTo(id);
            assertThat(speaker.getUuid()).isNotNull();
            assertThat(speaker.getVersion()).isEqualTo(2L);
            assertThat(speaker.getName()).isEqualTo(name);
            assertThat(speaker.getDescription()).isEqualTo(description);

            // delete speaker
            client.delete(id);

            // read back speaker
            speaker = client.read(id);
            assertThat(speaker).isNull();

        }
    }

}
