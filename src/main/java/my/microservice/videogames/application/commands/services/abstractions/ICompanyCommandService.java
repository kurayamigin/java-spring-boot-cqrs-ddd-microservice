package my.microservice.videogames.application.commands.services.abstractions;

import com.github.fge.jsonpatch.JsonPatch;
import my.microservice.videogames.application.commands.dtos.CompanyCommand;
import my.microservice.videogames.application.commands.dtos.GameCommand;
import my.microservice.videogames.cross_cutting.exceptions.EntityNotFoundException;

import java.util.List;

public interface ICompanyCommandService extends ICommandService<CompanyCommand, Long> {

    List<CompanyCommand> get();
    CompanyCommand getById(Long id) throws EntityNotFoundException;

    void create(CompanyCommand command);
    void delete(Long id) throws EntityNotFoundException;
    void patch(Long id, JsonPatch patch) throws EntityNotFoundException;
}
