package com.prodyna.pac.conference.service;

import org.glassfish.jersey.jackson.JacksonFeature;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import java.net.URI;

/**
 * @param <T> Type of request/response object.
 * @author Markus Konrad <markus.konrad@prodyna.com>
 */
public abstract class ResourceClient<T> implements AutoCloseable {

    protected final Client client;
    protected final WebTarget resource;

    protected final Class<T> resourceType;

    protected ResourceClient(final URI resourcePath, final Class<T> resourceType) {
        this.client = ClientBuilder.newClient().register(JacksonFeature.class);
        this.resource = client.target(resourcePath);
        this.resourceType = resourceType;
    }

    public long create(T resource) {
        Entity<T> entity = Entity.entity(resource, MediaType.APPLICATION_JSON_TYPE);
        return this.resource.request(MediaType.APPLICATION_JSON_TYPE).post(entity, Long.class);
    }

    public T read(long id) {
        WebTarget webTarget = resource.path(Long.toString(id));
        return webTarget.request(MediaType.APPLICATION_JSON_TYPE).get(resourceType);
    }

    public T update(T resource) {
        Entity<T> entity = Entity.entity(resource, MediaType.APPLICATION_JSON_TYPE);
        return this.resource.request(MediaType.APPLICATION_JSON_TYPE).put(entity, resourceType);
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
