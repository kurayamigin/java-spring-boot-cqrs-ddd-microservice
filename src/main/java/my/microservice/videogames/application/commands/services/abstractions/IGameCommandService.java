package my.microservice.videogames.application.commands.services.abstractions;

import com.github.fge.jsonpatch.JsonPatch;
import my.microservice.videogames.application.commands.dtos.GameCommand;
import my.microservice.videogames.application.queries.dtos.GameQuery;
import my.microservice.videogames.cross_cutting.exceptions.EntityNotFoundException;

import java.util.List;
import java.util.Optional;

public interface IGameCommandService {
    List<GameCommand> get();
    GameCommand getById(Long id) throws EntityNotFoundException;

    void create(GameCommand command);
    void delete(Long id) throws EntityNotFoundException;
    void patch(Long id, JsonPatch patch) throws EntityNotFoundException;
}
