package com.prodyna.pac.conference.datamodel;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "Talk")
@EntityListeners({TalkChangeEntityListener.class})
public class Talk extends EntityBase {

    private static final long serialVersionUID = -1840654869003298339L;

    private String name;
    private String description;
    private long startTime;
    private long endTime;
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

}
