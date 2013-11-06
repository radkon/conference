package com.prodyna.pac.conference.room;

import com.prodyna.pac.conference.core.rest.RestResource;

import javax.ejb.Stateless;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * @author Markus Konrad <markus.konrad@prodyna.com>
 */
@Stateless
@Path("room")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class RoomResource extends RestResource<Room> {

    protected RoomResource() {
        super(Room.class);
    }

    @POST
    @Override
    public long create(Room entity) {
        return super.create(entity);
    }

    @GET
    @Path("{id}")
    @Override
    public Room read(@PathParam("id") long id) {
        return super.read(id);
    }

    @PUT
    @Path("{id}")
    @Override
    public Room update(@PathParam("id") long id, Room entity) {
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
    public List<Room> findAll() {
        return super.findAll();
    }

    @POST
    @Path("{id}")
    public Room save(@PathParam("id") long id, Room entity) {
        return super.update(id, entity);
    }

}
