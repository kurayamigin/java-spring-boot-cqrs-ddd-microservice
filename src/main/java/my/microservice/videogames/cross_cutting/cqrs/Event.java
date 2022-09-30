package my.microservice.videogames.cross_cutting.cqrs;

import java.util.Date;
import java.util.UUID;

public class Event {
    public final UUID id = UUID.randomUUID();
    public final Date created = new Date();
    public String name;

    public Event(String name) {
        this.name = name;
    }
}
