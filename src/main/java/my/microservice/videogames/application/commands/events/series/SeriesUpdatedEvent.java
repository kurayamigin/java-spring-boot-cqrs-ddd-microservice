package my.microservice.videogames.application.commands.events.series;

import my.artifacts.Event;
import my.microservice.videogames.domain.models.Series;
import my.microservice.videogames.infrastructure.events.SeriesEvents;

public class SeriesUpdatedEvent extends Event {
    private Series series;

    public SeriesUpdatedEvent(Series series) {
        super(SeriesEvents.UPDATED);
        this.series = series;
    }

    public Series getSeries() {
        return series;
    }

    public void setSeries(Series series) {
        this.series = series;
    }
}
