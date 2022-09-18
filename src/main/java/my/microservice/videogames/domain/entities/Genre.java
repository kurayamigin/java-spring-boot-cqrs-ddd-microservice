package my.microservice.videogames.domain.entities;

import my.artifacts.models.AuditableLogicEntity;

import javax.persistence.Entity;

@Entity
public class Genre extends AuditableLogicEntity<Long> {
    private String name;
    private String description;

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
}
