package my.microservice.videogames.application.commands.events.series;

import my.microservice.videogames.cross_cutting.cqrs.Event;
import my.microservice.videogames.cross_cutting.events.CompanyEvents;
import my.microservice.videogames.cross_cutting.events.SeriesEvents;
import my.microservice.videogames.domain.models.Company;

public class SeriesDeletedEvent extends Event {
    private Long deletedId;

    public SeriesDeletedEvent(Long id) {
        super(SeriesEvents.DELETED);
        this.deletedId = id;
    }

    public Long getDeletedId() {
        return deletedId;
    }

    public void setDeletedId(Long deletedId) {
        this.deletedId = deletedId;
    }
}
