package com.prodyna.pac.conference.service;

import com.prodyna.pac.conference.core.RestResource;
import com.prodyna.pac.conference.entity.Talk;

import javax.ejb.Stateless;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * @author Markus Konrad <markus.konrad@prodyna.com>
 */
@Stateless
@Path("talk")
@Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
public class TalkResource extends RestResource<Talk> {

    protected TalkResource() {
        super(Talk.class);
    }

    @POST
    @Override
    public long create(Talk entity) {
        return super.create(entity);
    }

    @PUT
    @Override
    public Talk update(Talk entity) {
        return super.update(entity);
    }

    @GET
    @Path("{id}")
    @Override
    public Talk read(@PathParam("id") long id) {
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
    public List<Talk> findAll() {
        return super.findAll();
    }

    @GET
    @Path("_findRange")
    @Override
    public List<Talk> findRange(@QueryParam("from") Integer from, @QueryParam("to") Integer to) {
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
