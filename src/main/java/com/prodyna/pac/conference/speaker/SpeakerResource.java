package com.prodyna.pac.conference.speaker;

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
@Path("speaker")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class SpeakerResource extends RestResource<Speaker> {

    public SpeakerResource() {
        super(Speaker.class);
    }

    @POST
    @Override
    public long create(Speaker entity) {
        return super.create(entity);
    }

    @GET
    @Path("{id}")
    @Override
    public Speaker read(@PathParam("id") long id) {
        return super.read(id);
    }

    @PUT
    @Path("{id}")
    @Override
    public Speaker update(@PathParam("id") long id, Speaker entity) {
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
    public List<Speaker> findAll() {
        return super.findAll();
    }
}
