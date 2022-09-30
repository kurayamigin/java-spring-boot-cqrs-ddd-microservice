package my.microservice.videogames.application.commands.services.abstractions;

import com.github.fge.jsonpatch.JsonPatch;
import my.microservice.videogames.application.commands.dtos.GameCommand;
import my.microservice.videogames.application.commands.dtos.SeriesCommand;
import my.microservice.videogames.cross_cutting.exceptions.EntityNotFoundException;

import java.util.List;

public interface ISeriesCommandService extends ICommandService<SeriesCommand, Long> {
    List<SeriesCommand> get();
    SeriesCommand getById(Long id) throws EntityNotFoundException;

    void create(SeriesCommand command);
    void delete(Long id) throws EntityNotFoundException;
    void patch(Long id, JsonPatch patch) throws EntityNotFoundException;
}
