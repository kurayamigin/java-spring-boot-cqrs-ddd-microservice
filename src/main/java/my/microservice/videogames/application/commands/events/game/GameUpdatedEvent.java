package my.microservice.videogames.application.commands.events.game;

import my.artifacts.Event;
import my.microservice.videogames.infrastructure.events.GameEvents;
import my.microservice.videogames.domain.models.Game;

public class GameUpdatedEvent extends Event {
    private Game updatedGame;

    public GameUpdatedEvent(Game updatedGame) {
        super(GameEvents.UPDATED);
        this.updatedGame = updatedGame;
    }

    public Game getUpdatedGame() {
        return updatedGame;
    }

    public void setUpdatedGame(Game updatedGame) {
        this.updatedGame = updatedGame;
    }
}
