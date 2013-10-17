package com.prodyna.pac.conference.datamodel;

import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Markus Konrad <markus.konrad@prodyna.com>
 */
@Entity
@Table(name = "Speaker")
@NamedQueries({
    @NamedQuery(name = "Speaker.findAll", query = "select s from Speaker s")})
public class Speaker extends EntityBase {

    private static final long serialVersionUID = 1L;
    private String name;
    private String email;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getUuid());
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
        return "Speaker[ id=" + getId() + " ]";
    }
}
