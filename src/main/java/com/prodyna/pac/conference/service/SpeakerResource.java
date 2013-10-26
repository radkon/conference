package com.prodyna.pac.conference.service;

import com.prodyna.pac.conference.core.RestResource;
import com.prodyna.pac.conference.entity.Speaker;
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
@Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
public class SpeakerResource extends RestResource<Speaker> {

    public SpeakerResource() {
        super(Speaker.class);
    }

    @POST
    @Override
    public long create(Speaker entity) {
        return super.create(entity);
    }

    @PUT
    @Override
    public Speaker update(Speaker entity) {
        return super.update(entity);
    }

    @GET
    @Path("{id}")
    @Override
    public Speaker read(@PathParam("id") long id) {
        return super.read(id);
    }

    @DELETE
    @Path("{id}")
    @Override
    public void remove(@PathParam("id") long id) {
        super.remove(id);
    }

    @GET
    @Path("_findAll")
    @Override
    public List<Speaker> findAll() {
        return super.findAll();
    }

    @GET
    @Path("_findRange")
    @Override
    public List<Speaker> findRange(@QueryParam("from") Integer from, @QueryParam("to") Integer to) {
        return super.findRange(from, to);
    }

    @GET
    @Path("_count")
    @Produces(MediaType.TEXT_PLAIN)
    @Override
    public long count() {
        return super.count();
    }

}
