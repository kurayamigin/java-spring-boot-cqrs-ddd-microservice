package my.microservice.videogames.application.commands.events.company;

import my.artifacts.Event;
import my.microservice.videogames.infrastructure.events.CompanyEvents;

public class CompanyDeletedEvent extends Event {
    private Long deletedId;
    public CompanyDeletedEvent(Long id) {
        super(CompanyEvents.DELETED);
        this.deletedId = id;
    }


    public Long getDeletedId() {
        return deletedId;
    }

    public void setDeletedId(Long deletedId) {
        this.deletedId = deletedId;
    }
}
