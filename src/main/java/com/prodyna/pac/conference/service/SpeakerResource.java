package com.prodyna.pac.conference.service;

import com.prodyna.pac.conference.datamodel.Speaker;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

/**
 *
 * @author Markus Konrad <markus.konrad@prodyna.com>
 */
@Stateless
@Path("speaker")
public class SpeakerResource extends RestResource<Speaker> {
    
    @PersistenceContext(unitName = "ConferencePU")
    private EntityManager em;

    public SpeakerResource() {
        super(Speaker.class);
    }

    @PUT
    @Consumes({"application/xml", "application/json"})
    @Override
    public void save(Speaker entity) {
        super.save(entity);
    }

    @GET
    @Path("{id}")
    @Produces({"application/xml", "application/json"})
    @Override
    public Speaker findById(@PathParam("id") Long id) {
        return super.findById(id);
    }

    @DELETE
    @Path("{id}")
    @Override
    public void remove(@PathParam("id") Long id) {
        super.remove(id);
    }

    @GET
    @Path("_findAll")
    @Produces({"application/xml", "application/json"})
    @Override
    public List<Speaker> findAll() {
        return super.findAll();
    }
    
    @GET
    @Path("_findRange")
    @Produces({"application/xml", "application/json"})
    @Override
    public List<Speaker> findRange(@QueryParam("from") Integer from, @QueryParam("to") Integer to) {
        return super.findRange(from,to);
    }

    @GET
    @Path("_count")
    @Produces("text/plain")
    @Override
    public int count() {
        return super.count();
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
