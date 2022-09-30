package my.microservice.videogames.application.commands.services.abstractions;

import com.github.fge.jsonpatch.JsonPatch;
import my.microservice.videogames.application.commands.dtos.CompanyCommand;
import my.microservice.videogames.application.commands.dtos.DeveloperCommand;
import my.microservice.videogames.application.commands.dtos.GameCommand;
import my.microservice.videogames.cross_cutting.exceptions.EntityNotFoundException;
import my.microservice.videogames.domain.models.Developer;

import java.util.List;

public interface IDeveloperCommandService extends ICommandService<DeveloperCommand, Long> {
    List<DeveloperCommand> get();
    DeveloperCommand getById(Long id) throws EntityNotFoundException;

    void create(DeveloperCommand command);
    void delete(Long id) throws EntityNotFoundException;
    void patch(Long id, JsonPatch patch) throws EntityNotFoundException;
}
