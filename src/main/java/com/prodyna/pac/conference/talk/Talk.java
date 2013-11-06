package com.prodyna.pac.conference.talk;

import com.prodyna.pac.conference.conference.Conference;
import com.prodyna.pac.conference.core.entity.EntityBase;
import com.prodyna.pac.conference.room.Room;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "Talk")
@EntityListeners({TalkChangeEntityListener.class})
@NamedQueries({@NamedQuery(name = Talk.FIND_BY_CONFERENCE, query = "SELECT t FROM Talk t WHERE t.conference = :conference"),
        @NamedQuery(name = Talk.FIND_AVAILABLE_ROOMS_BY_DURATION,
                query = "SELECT t.room FROM Talk t " +
                        "WHERE (t.startTime NOT BETWEEN :startTime AND :endTime) " +
                        "AND (t.endTime NOT BETWEEN :startTime AND :endTime) " +
                        "AND ((:startTime NOT BETWEEN t.startTime AND t.endTime) AND (:endTime NOT BETWEEN t.startTime AND t.endTime))")})
public class Talk extends EntityBase {

    private static final long serialVersionUID = -1840654869003298339L;

    public static final String FIND_BY_CONFERENCE = "Talk.findByConference";
    public static final String FIND_AVAILABLE_ROOMS_BY_DURATION = "Talk.findAvailableRoomsByDuration";

    private String name;
    private String description;
    private Date startTime;
    private Date endTime;
    private Conference conference;
    private Room room;

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

    @Temporal(TemporalType.TIMESTAMP)
    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    @Temporal(TemporalType.TIMESTAMP)
    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
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
