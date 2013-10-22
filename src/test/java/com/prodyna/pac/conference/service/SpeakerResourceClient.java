package com.prodyna.pac.conference.service;


import com.prodyna.pac.conference.datamodel.Speaker;
import org.glassfish.jersey.jackson.JacksonFeature;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import java.net.URI;

public class SpeakerResourceClient implements AutoCloseable {

    private Client client;
    private WebTarget resource;

    public SpeakerResourceClient(final URI resourcePath) {
        this.client = ClientBuilder.newClient().register(JacksonFeature.class);
        this.resource = client.target(resourcePath);
    }

    public long create(Speaker speaker) {
        Entity<Speaker> speakerEntity = Entity.entity(speaker, MediaType.APPLICATION_JSON_TYPE);
        return resource.request(MediaType.APPLICATION_JSON_TYPE).post(speakerEntity, Long.class);
    }

    public Speaker read(long id) {
        WebTarget webTarget = resource.path(Long.toString(id));
        return webTarget.request(MediaType.APPLICATION_JSON_TYPE).get(Speaker.class);
    }

    public Speaker update(Speaker speaker) {
        Entity<Speaker> speakerEntity = Entity.entity(speaker, MediaType.APPLICATION_JSON_TYPE);
        return resource.request(MediaType.APPLICATION_JSON_TYPE).put(speakerEntity, Speaker.class);
    }

    public void delete(long id) {
        WebTarget webTarget = resource.path(Long.toString(id));
        webTarget.request(MediaType.APPLICATION_JSON_TYPE).delete();
    }

    @Override
    public void close() throws Exception {
        client.close();
    }
}
