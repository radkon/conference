package com.prodyna.pac.conference.datamodel;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

/**
 * @author Markus Konrad <markus.konrad@prodyna.com>
 */
@MappedSuperclass
public abstract class EntityBase implements Serializable {

    private static final long serialVersionUID = -7225110533478088113L;
    private Long id;
    private UUID uuid;
    private Long version;

    @PrePersist
    private void ensureUuidIsSet() {
        if (getUuid() == null) {
            setUuid(UUID.randomUUID());
        }
    }

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    public Long getId() {
        return id;
    }

    protected void setId(Long id) {
        this.id = id;
    }

    // @NotNull
    public UUID getUuid() {
        return uuid;
    }

    protected void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    @Version
    public Long getVersion() {
        return version;
    }

    protected void setVersion(Long version) {
        this.version = version;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getUuid());
    }

}
