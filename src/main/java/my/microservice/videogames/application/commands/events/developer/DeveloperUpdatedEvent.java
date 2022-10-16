package my.microservice.videogames.application.commands.events.developer;

import my.artifacts.Event;
import my.microservice.videogames.infrastructure.events.DeveloperEvents;
import my.microservice.videogames.domain.models.Developer;

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
