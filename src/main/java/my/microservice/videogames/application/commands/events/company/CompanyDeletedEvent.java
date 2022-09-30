package my.microservice.videogames.application.commands.events.company;

import my.microservice.videogames.cross_cutting.cqrs.Event;
import my.microservice.videogames.cross_cutting.events.CompanyEvents;
import my.microservice.videogames.cross_cutting.events.DeveloperEvents;
import my.microservice.videogames.domain.models.Company;
import my.microservice.videogames.domain.models.Developer;

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
