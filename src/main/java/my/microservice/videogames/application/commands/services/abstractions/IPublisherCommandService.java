package my.microservice.videogames.application.commands.services.abstractions;

import com.github.fge.jsonpatch.JsonPatch;
import my.microservice.videogames.application.commands.dtos.PublisherCommand;
import my.microservice.videogames.application.commands.dtos.SeriesCommand;
import my.microservice.videogames.cross_cutting.exceptions.EntityNotFoundException;

import java.util.List;

public interface IPublisherCommandService extends ICommandService<PublisherCommand, Long> {
    List<PublisherCommand> get();
    PublisherCommand getById(Long id) throws EntityNotFoundException;

    void create(PublisherCommand command);
    void delete(Long id) throws EntityNotFoundException;
    void patch(Long id, JsonPatch patch) throws EntityNotFoundException;
}
