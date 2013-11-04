package com.prodyna.pac.conference.core.test;

import com.prodyna.pac.conference.core.entity.EntityBase;
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
public abstract class ResourceClient<T extends EntityBase> implements AutoCloseable {

    protected final Client client;
    protected final WebTarget root;

    protected final Class<T> resourceType;

    protected ResourceClient(final URI resourcePath, final Class<T> resourceType) {
        this.client = ClientBuilder.newClient().register(JacksonFeature.class);
        this.root = client.target(resourcePath);
        this.resourceType = resourceType;
    }

    public long create(T resource) {
        WebTarget webTarget = root;
        Entity<T> entity = Entity.entity(resource, MediaType.APPLICATION_JSON_TYPE);
        return webTarget.request(MediaType.APPLICATION_JSON_TYPE).post(entity, Long.class);
    }

    public T read(long id) {
        WebTarget webTarget = root.path(Long.toString(id));
        return webTarget.request(MediaType.APPLICATION_JSON_TYPE).get(resourceType);
    }

    public T update(T resource) {
        WebTarget webTarget = root.path(Long.toString(resource.getId()));
        Entity<T> entity = Entity.entity(resource, MediaType.APPLICATION_JSON_TYPE);
        return webTarget.request(MediaType.APPLICATION_JSON_TYPE).put(entity, resourceType);
    }

    public void delete(long id) {
        WebTarget webTarget = root.path(Long.toString(id));
        webTarget.request(MediaType.APPLICATION_JSON_TYPE).delete();
    }

    @Override
    public void close() throws Exception {
        client.close();
    }
}
