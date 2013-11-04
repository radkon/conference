package com.prodyna.pac.conference.speaker;

import com.prodyna.pac.conference.core.entity.EntityBase;

import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * @author Markus Konrad <markus.konrad@prodyna.com>
 */
@Entity
@Table(name = "Speaker")
@NamedQueries({
        @NamedQuery(name = "Speaker.findAll", query = "select s from Speaker s")})
public class Speaker extends EntityBase {

    private static final long serialVersionUID = 1L;
    private String name;
    private String description;

    public Speaker() {
        // ok
    }

    public Speaker(String name, String description) {
        this.name = name;
        this.description = description;
    }

    @NotNull
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @NotNull
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object obj) {
        final boolean result;
        if (obj instanceof Speaker) {
            Speaker other = (Speaker) obj;
            result = Objects.equals(getUuid(), other.getUuid());
        } else {
            result = false;
        }
        return result;
    }

    @Override
    public String toString() {
        return "Speaker[ id=" + getId() + ", name=" + getName() + ", description=" + getDescription() + " ]";
    }

}
