package my.microservice.videogames.application.commands.services.abstractions;

import com.github.fge.jsonpatch.JsonPatch;
import my.microservice.videogames.application.commands.dtos.GameCommand;
import my.microservice.videogames.cross_cutting.exceptions.EntityNotFoundException;

import java.util.List;

public interface IGameCommandService extends ICommandService<GameCommand, Long> {
}
