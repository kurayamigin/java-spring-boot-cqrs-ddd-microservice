package my.microservice.videogames.application.services.commands.abstractions;

import my.microservice.videogames.application.dtos.commands.GameCommand;
import my.microservice.videogames.application.dtos.queries.GameQuery;

import java.util.List;

public interface IGameCommandService {
    List<GameQuery> get();

    void create(GameCommand command);
}
