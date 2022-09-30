package my.microservice.videogames.application.commands.events.series;

import my.microservice.videogames.cross_cutting.cqrs.Event;
import my.microservice.videogames.cross_cutting.events.SeriesEvents;
import my.microservice.videogames.domain.models.Serie;

public class SeriesCreatedEvent extends Event {
    private Serie series;

    public SeriesCreatedEvent(Serie series) {
        super(SeriesEvents.CREATED);
        this.series = series;
    }

    public Serie getSeries() {
        return series;
    }

    public void setSeries(Serie serie) {
        this.series = serie;
    }
}
