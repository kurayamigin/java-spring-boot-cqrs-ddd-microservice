package my.microservice.videogames.application.commands.events.developer;

import my.microservice.videogames.cross_cutting.cqrs.Event;
import my.microservice.videogames.cross_cutting.events.DeveloperEvents;
import my.microservice.videogames.cross_cutting.events.GameEvents;
import my.microservice.videogames.domain.models.Developer;
import my.microservice.videogames.domain.models.Game;

public class DeveloperUpdatedEvent extends Event {
    private Developer developer;

    public DeveloperUpdatedEvent(Developer developer) {
        super(DeveloperEvents.UPDATED);
        this.developer = developer;
    }

    public Developer getDeveloper() {
        return developer;
    }

    public void setDeveloper(Developer developer) {
        this.developer = developer;
    }
}
