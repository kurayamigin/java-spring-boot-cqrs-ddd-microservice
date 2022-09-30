package my.microservice.videogames.application.commands.events.publisher;

import my.microservice.videogames.cross_cutting.cqrs.Event;
import my.microservice.videogames.cross_cutting.events.CompanyEvents;
import my.microservice.videogames.cross_cutting.events.PublisherEvents;
import my.microservice.videogames.domain.models.Company;
import my.microservice.videogames.domain.models.Publisher;

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
