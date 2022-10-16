package my.microservice.videogames.application.commands.services.abstractions;

import com.github.fge.jsonpatch.JsonPatch;
import my.microservice.videogames.application.commands.dtos.PublisherCommand;
import my.microservice.videogames.cross_cutting.exceptions.EntityNotFoundException;

import java.util.List;

public interface IPublisherCommandService extends ICommandService<PublisherCommand, Long> {
}
