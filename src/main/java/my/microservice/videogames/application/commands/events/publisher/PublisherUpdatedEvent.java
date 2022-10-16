package my.microservice.videogames.application.commands.events.publisher;

import my.artifacts.Event;
import my.microservice.videogames.infrastructure.events.PublisherEvents;
import my.microservice.videogames.domain.models.Publisher;

public class PublisherUpdatedEvent extends Event {
    private Publisher publisher;

    public PublisherUpdatedEvent(Publisher publisher) {
        super(PublisherEvents.UPDATED);
        this.publisher = publisher;
    }

    public Publisher getPublisher() {
        return publisher;
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }
}
