package my.microservice.videogames.application.commands.events.developer;

import my.artifacts.Event;
import my.microservice.videogames.infrastructure.events.DeveloperEvents;

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
