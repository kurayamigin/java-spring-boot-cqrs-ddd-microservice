package my.microservice.videogames.application.commands.events.developer;

import my.microservice.videogames.cross_cutting.cqrs.Event;
import my.microservice.videogames.cross_cutting.events.DeveloperEvents;
import my.microservice.videogames.cross_cutting.events.GameEvents;
import my.microservice.videogames.domain.models.Developer;

public class DeveloperDeletedEvent extends Event {
    private Long deletedId;

    public DeveloperDeletedEvent(Long id) {
         super(DeveloperEvents.DELETED);
         this.deletedId = id;
    }

    public Long getDeletedId() {
        return deletedId;
    }

    public void setDeletedId(Long deletedId) {
        this.deletedId = deletedId;
    }
}
