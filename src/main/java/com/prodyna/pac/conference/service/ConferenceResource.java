package com.prodyna.pac.conference.service;

import com.prodyna.pac.conference.core.RestResource;
import com.prodyna.pac.conference.entity.Conference;

import javax.ejb.Stateless;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * @author Markus Konrad <markus.konrad@prodyna.com>
 */
@Stateless
@Path("conference")
@Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
public class ConferenceResource extends RestResource<Conference> {

    protected ConferenceResource() {
        super(Conference.class);
    }

    @POST
    @Override
    public long create(Conference entity) {
        return super.create(entity);
    }

    @PUT
    @Override
    public Conference update(Conference entity) {
        return super.update(entity);
    }

    @GET
    @Path("id")
    @Override
    public Conference read(@PathParam("id") long id) {
        return super.read(id);
    }

    @DELETE
    @Path("id")
    @Override
    public void remove(@PathParam("id") long id) {
        super.remove(id);
    }

    @GET
    @Path("_findAll")
    @Override
    public List<Conference> findAll() {
        return super.findAll();
    }

    @GET
    @Path("_findRange")
    @Override
    public List<Conference> findRange(@QueryParam("from") Integer from, @QueryParam("to") Integer to) {
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
