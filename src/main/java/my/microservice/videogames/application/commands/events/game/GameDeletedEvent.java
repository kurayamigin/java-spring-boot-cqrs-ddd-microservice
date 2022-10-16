package my.microservice.videogames.application.commands.events.game;

import my.artifacts.Event;
import my.microservice.videogames.infrastructure.events.GameEvents;

public class GameDeletedEvent extends Event {
    private Long deletedId;

    public GameDeletedEvent(Long deletedId) {
        super(GameEvents.DELETED);
        this.deletedId = deletedId;
    }

    public Long getDeletedId() {
        return deletedId;
    }

    public void setDeletedId(Long deletedId) {
        this.deletedId = deletedId;
    }
}
