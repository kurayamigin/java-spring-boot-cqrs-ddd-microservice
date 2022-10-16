package my.microservice.videogames.application.commands.services.abstractions;

import com.github.fge.jsonpatch.JsonPatch;
import my.microservice.videogames.application.commands.dtos.DeveloperCommand;
import my.microservice.videogames.cross_cutting.exceptions.EntityNotFoundException;

import java.util.List;

public interface IDeveloperCommandService extends ICommandService<DeveloperCommand, Long> {
}
