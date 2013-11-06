package com.prodyna.pac.conference.conference;

import com.prodyna.pac.conference.core.rest.RestResource;
import com.prodyna.pac.conference.monitoring.Monitored;

import javax.ejb.Stateless;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * @author Markus Konrad <markus.konrad@prodyna.com>
 */
@Monitored
@Stateless
@Path("conference")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ConferenceResource extends RestResource<Conference> {

    protected ConferenceResource() {
        super(Conference.class);
    }

    @POST
    @Override
    public long create(Conference entity) {
        return super.create(entity);
    }

    @GET
    @Path("{id}")
    @Override
    public Conference read(@PathParam("id") long id) {
        return super.read(id);
    }

    @PUT
    @Path("{id}")
    @Override
    public Conference update(@PathParam("id") long id, Conference entity) {
        return super.update(id, entity);
    }

    @DELETE
    @Path("{id}")
    @Override
    public void delete(@PathParam("id") long id) {
        super.delete(id);
    }

    @GET
    @Override
    public List<Conference> findAll() {
        return super.findAll();
    }

    @POST
    @Path("{id}")
    public Conference save(@PathParam("id") long id, Conference entity) {
        return super.update(id, entity);
    }
}
