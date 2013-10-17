package com.prodyna.pac.conference.datamodel;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

/**
 * @author Markus Konrad <markus.konrad@prodyna.com>
 */
@MappedSuperclass
public abstract class EntityBase implements Serializable {

    private Long id;
    private UUID uuid;
    private Long version;

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

    @PrePersist
    private void prePersist() {
        if (getUuid() == null) {
            setUuid(UUID.randomUUID());
        }
    }

}
