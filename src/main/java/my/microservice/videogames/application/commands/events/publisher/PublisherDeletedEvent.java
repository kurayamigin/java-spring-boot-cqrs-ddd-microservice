package my.microservice.videogames.application.commands.events.publisher;

import my.artifacts.Event;
import my.microservice.videogames.infrastructure.events.PublisherEvents;

public class PublisherDeletedEvent extends Event {
    private Long deletedId;

    public PublisherDeletedEvent(Long deletedId) {
        super(PublisherEvents.DELETED);
        this.deletedId = deletedId;

    }

    public Long getDeletedId() {
        return deletedId;
    }

    public void setDeletedId(Long deletedId) {
        this.deletedId = deletedId;
    }
}
