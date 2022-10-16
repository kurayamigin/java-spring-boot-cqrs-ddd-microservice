package my.microservice.videogames.application.commands.events.series;

import my.artifacts.Event;
import my.microservice.videogames.infrastructure.events.SeriesEvents;

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
