package com.prodyna.pac.conference.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Objects;

@Table(name = "Room")
@Entity
public class Room extends EntityBase {

    private static final long serialVersionUID = -4357373164103483388L;

    private String name;
    private int capacity;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    @Override
    public boolean equals(Object obj) {
        final boolean result;
        if (obj instanceof Room) {
            Room other = (Room) obj;
            result = Objects.equals(getUuid(), other.getUuid());
        } else {
            result = false;
        }
        return result;
    }
}
