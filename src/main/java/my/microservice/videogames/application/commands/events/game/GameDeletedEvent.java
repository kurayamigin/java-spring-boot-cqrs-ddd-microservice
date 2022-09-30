package my.microservice.videogames.application.commands.events.game;

import my.microservice.videogames.cross_cutting.cqrs.Event;
import my.microservice.videogames.cross_cutting.events.GameEvents;
import my.microservice.videogames.domain.models.Game;

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
