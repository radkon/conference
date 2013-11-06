package com.prodyna.pac.conference.talk;

import com.prodyna.pac.conference.core.entity.EntityBase;
import com.prodyna.pac.conference.speaker.Speaker;

import javax.persistence.*;
import java.util.Objects;

/**
 * @author Markus Konrad <markus.konrad@prodyna.com>
 */
@Entity
@Table(name = "TalkSpeakerAssignment")
@NamedQueries({@NamedQuery(name = TalkSpeakerAssignment.FIND_SPEAKERS_BY_TALK,
        query = "SELECT tsa.speaker FROM TalkSpeakerAssignment tsa WHERE tsa.talk.id = :talkId"),
        @NamedQuery(name = TalkSpeakerAssignment.FIND_AVAILABLE_SPEAKERS_BY_DURATION,
        query = "SELECT tsa.speaker FROM TalkSpeakerAssignment tsa " +
                "WHERE (tsa.talk.startTime NOT BETWEEN :startTime AND :endTime) " +
                "AND (tsa.talk.endTime NOT BETWEEN :startTime AND :endTime) " +
                "AND ((:startTime NOT BETWEEN tsa.talk.startTime AND tsa.talk.endTime) AND (:endTime NOT BETWEEN tsa.talk.startTime AND tsa.talk.endTime))")})
public class TalkSpeakerAssignment extends EntityBase {

    private static final long serialVersionUID = -1203686522223728734L;

    public static final String FIND_SPEAKERS_BY_TALK = "TalkSpeakerAssignment.findSpeakersByTalk";
    public static final String FIND_AVAILABLE_SPEAKERS_BY_DURATION = "TalkSpeakerAssignment.findAvailableSpeakersByDuration";

    @ManyToOne
    private Talk talk;

    @ManyToOne
    private Speaker speaker;

    TalkSpeakerAssignment() {
        // ok
    }

    public TalkSpeakerAssignment(final Talk talk, final Speaker speaker) {
        this.talk = talk;
        this.speaker = speaker;
    }

    public Talk getTalk() {
        return talk;
    }

    public void setTalk(Talk talk) {
        this.talk = talk;
    }

    public Speaker getSpeaker() {
        return speaker;
    }

    public void setSpeaker(Speaker speaker) {
        this.speaker = speaker;
    }

    @Override
    public boolean equals(Object obj) {
        final boolean result;
        if (obj instanceof TalkSpeakerAssignment) {
            TalkSpeakerAssignment other = (TalkSpeakerAssignment) obj;
            result = Objects.equals(getUuid(), other.getUuid());
        } else {
            result = false;
        }
        return result;
    }

    @Override
    public String toString() {
        return "TalkSpeakerAssignment{" +
                "talk=" + (talk == null ? null : talk.getId()) +
                ", speaker=" + (speaker == null ? null : speaker.getId()) +
                '}';
    }
}
