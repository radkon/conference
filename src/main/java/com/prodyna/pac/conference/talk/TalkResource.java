package com.prodyna.pac.conference.talk;

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

    @GET
    @Path("{id}")
    @Override
    public Talk read(@PathParam("id") long id) {
        return super.read(id);
    }

    @PUT
    @Path("{id}")
    @Override
    public Talk update(@PathParam("id") long id, Talk entity) {
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
    public List<Talk> findAll() {
        return super.findAll();
    }

    @POST
    @Path("{id}")
    public Talk save(@PathParam("id") long id, Talk entity) {
        return super.update(id, entity);
    }

}
