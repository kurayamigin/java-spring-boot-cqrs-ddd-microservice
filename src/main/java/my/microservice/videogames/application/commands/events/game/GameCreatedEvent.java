package my.microservice.videogames.application.commands.events.game;

import my.microservice.videogames.cross_cutting.cqrs.Event;
import my.microservice.videogames.cross_cutting.events.GameEvents;
import my.microservice.videogames.domain.models.Game;

public class GameCreatedEvent extends Event {
    private Game createdGame;

    public GameCreatedEvent(Game createdGame) {
        super(GameEvents.CREATED);
        this.createdGame = createdGame;
    }

    public Game getCreatedGame() {
        return createdGame;
    }

    public void setCreatedGame(Game createdGame) {
        this.createdGame = createdGame;
    }
}
