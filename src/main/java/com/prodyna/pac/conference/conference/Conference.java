package com.prodyna.pac.conference.conference;

import com.prodyna.pac.conference.core.entity.EntityBase;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.sql.Date;
import java.util.Objects;

@Table(name = "Conference")
@Entity
public class Conference extends EntityBase {

    private static final long serialVersionUID = 7225844642218698951L;

    private String name;

    private String description;

    private String location;

    private Date startDate;

    private Date endDate;

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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    @Override
    public boolean equals(Object obj) {
        final boolean result;
        if (obj instanceof Conference) {
            Conference other = (Conference) obj;
            result = Objects.equals(getUuid(), other.getUuid());
        } else {
            result = false;
        }
        return result;
    }
}