package my.microservice.videogames.application.commands.services.abstractions;

import com.github.fge.jsonpatch.JsonPatch;
import my.microservice.videogames.application.commands.dtos.GameCommand;
import my.microservice.videogames.application.commands.dtos.ICommand;
import my.microservice.videogames.cross_cutting.exceptions.EntityNotFoundException;

import java.util.List;

public interface ICommandService<TCommand extends ICommand, TKey> {
    List<TCommand> get();
    TCommand getById(TKey id) throws EntityNotFoundException;

    void create(TCommand command);
    void delete(TKey id) throws EntityNotFoundException;
    void patch(TKey id, JsonPatch patch) throws EntityNotFoundException;
}
