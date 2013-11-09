package com.prodyna.pac.conference.talk;

import com.prodyna.pac.conference.conference.ConferenceResource;
import com.prodyna.pac.conference.core.rest.RestResource;
import com.prodyna.pac.conference.monitoring.Monitored;
import com.prodyna.pac.conference.speaker.Speaker;

import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.inject.Inject;
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

    @Inject
    private Event<TalkValidationEvent> validationEvents;

    @Inject
    private ConferenceResource conferenceResource;

    protected TalkResource() {
        super(Talk.class);
    }

    @POST
    @Override
    public long create(Talk entity) {
        validationEvents.fire(new TalkValidationEvent(entity));
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
        validationEvents.fire(new TalkValidationEvent(entity));
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

    @GET
    @Path("_findByConference")
    public List<Talk> findByConference(@QueryParam("conferenceId") long conferenceId) {
        return getEntityManager().createNamedQuery(Talk.FIND_BY_CONFERENCE, Talk.class)
                .setParameter("conferenceId", conferenceId)
                .getResultList();
    }

    @GET
    @Path("_checkRoomAvailableByTalkDuration")
    public Long checkRoomAvailableByTalkDuration(@QueryParam("roomId") long roomId,
                                                 @QueryParam("startTime") long startTime,
                                                 @QueryParam("endTime") long endTime) {
        return getEntityManager().createNamedQuery(Talk.CHECK_ROOM_AVAILABLE_BY_TALK_DURATION, Long.class)
                .setParameter("roomId", roomId)
                .setParameter("startTime", startTime)
                .setParameter("endTime", endTime)
                .getSingleResult();
    }

    @GET
    @Path("_checkSpeakerAvailableByTalkDuration")
    public Long checkSpeakerAvailableByTalkDuration(@QueryParam("speaker") Speaker speaker,
                                                    @QueryParam("startTime") long startTime,
                                                    @QueryParam("endTime") long endTime) {
        return getEntityManager().createNamedQuery(Talk.CHECK_SPEAKER_AVAILABLE_BY_TALK_DURATION, Long.class)
                .setParameter("speaker", speaker)
                .setParameter("startTime", startTime)
                .setParameter("endTime", endTime)
                .getSingleResult();
    }

    @GET
    @Path("_findAvailableSpeakerByTalkDuration")
    public List<Speaker> findAvailableSpeakerByTalkDuration(@QueryParam("startTime") long startTime,
                                                            @QueryParam("endTime") long endTime) {
        return getEntityManager().createNamedQuery(TalkSpeakerAssignment.FIND_AVAILABLE_SPEAKERS_BY_DURATION,
                Speaker.class)
                .setParameter("startTime", startTime)
                .setParameter("endTime", endTime)
                .getResultList();
    }

    @GET
    @Path("_findSpeakersByTalk")
    public List<Speaker> findSpeakersByTalk(@QueryParam("talkId") long talkId) {
        return getEntityManager().createNamedQuery(TalkSpeakerAssignment.FIND_SPEAKERS_BY_TALK, Speaker.class)
                .setParameter("talkId", talkId)
                .getResultList();
    }

}
