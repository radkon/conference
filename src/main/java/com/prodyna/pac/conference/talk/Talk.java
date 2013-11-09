package com.prodyna.pac.conference.talk;

import com.prodyna.pac.conference.conference.Conference;
import com.prodyna.pac.conference.core.entity.EntityBase;
import com.prodyna.pac.conference.room.Room;
import com.prodyna.pac.conference.speaker.Speaker;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "Talk")
@EntityListeners({TalkEntityListener.class})
@NamedQueries({@NamedQuery(name = Talk.FIND_BY_CONFERENCE,
        query = "SELECT t FROM Talk t " +
                "WHERE t.conference.id = :conferenceId"),
        @NamedQuery(name = Talk.CHECK_ROOM_AVAILABLE_BY_TALK_DURATION,
                query = "SELECT COUNT(talk) FROM Talk talk " +
                        "WHERE talk.room.id = :roomId " +
                        "AND (" +
                            "(talk.startTime BETWEEN :startTime AND :endTime) " +
                            "OR (talk.endTime BETWEEN :startTime AND :endTime) " +
                            "OR (" +
                                "(:startTime BETWEEN talk.startTime AND talk.endTime) " +
                                "AND (:endTime BETWEEN talk.startTime AND talk.endTime))" +
                        ")"),
        @NamedQuery(name = Talk.CHECK_SPEAKER_AVAILABLE_BY_TALK_DURATION,
                query = "SELECT COUNT(talk) FROM Talk talk " +
                        "WHERE :speaker IN (talk.speakers) " +
                        "AND talk.speaker NOT IN (SELECT talk.speakers FROM Talk t WHERE (t.startTime NOT BETWEEN :startTime" +
                        " AND :endTime) " +
                        "AND (t.endTime NOT BETWEEN :startTime AND :endTime) " +
                        "AND ((:startTime NOT BETWEEN t.startTime AND t.endTime) AND (:endTime NOT BETWEEN t" +
                        ".startTime AND t.endTime)))")})
public class Talk extends EntityBase {

    private static final long serialVersionUID = -1840654869003298339L;

    public static final String FIND_BY_CONFERENCE = "Talk.findByConference";
    public static final String CHECK_ROOM_AVAILABLE_BY_TALK_DURATION = "Talk.checkRoomAvailableByDuration";
    public static final String CHECK_SPEAKER_AVAILABLE_BY_TALK_DURATION = "Talk.checkSpeakerAvailableByDuration";

    private String name;
    private String description;
    private long startTime;
    private long endTime;
    private Conference conference;
    private Room room;
    private Set<Speaker> speakers;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public long getEndTime() {
        return endTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "talkspeakerassignment",
            joinColumns = @JoinColumn(name = "talk_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "speaker_id", referencedColumnName = "id"))
    public Set<Speaker> getSpeakers() {
        return speakers;
    }

    public void setSpeakers(Set<Speaker> speakers) {
        this.speakers = speakers;
    }

    @ManyToOne
    public Conference getConference() {
        return conference;
    }

    public void setConference(Conference conference) {
        this.conference = conference;
    }

    @ManyToOne
    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    @Override
    public boolean equals(Object obj) {
        final boolean result;
        if (obj instanceof Talk) {
            Talk other = (Talk) obj;
            result = Objects.equals(getUuid(), other.getUuid());
        } else {
            result = false;
        }
        return result;
    }

    @Override
    public String toString() {
        return "Talk{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", conference=" + (conference == null ? null : conference.getId()) +
                ", room=" + (room == null ? null : room.getId()) +
                '}';
    }
}
