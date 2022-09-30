package my.microservice.videogames.application.commands.services;

import com.github.fge.jsonpatch.JsonPatch;
import my.microservice.videogames.application.commands.dtos.DeveloperCommand;
import my.microservice.videogames.application.commands.dtos.GameCommand;
import my.microservice.videogames.application.commands.events.developer.DeveloperCreatedEvent;
import my.microservice.videogames.application.commands.events.developer.DeveloperDeletedEvent;
import my.microservice.videogames.application.commands.events.developer.DeveloperUpdatedEvent;
import my.microservice.videogames.application.commands.events.game.GameDeletedEvent;
import my.microservice.videogames.application.commands.events.game.GameUpdatedEvent;
import my.microservice.videogames.application.commands.services.abstractions.IDeveloperCommandService;
import my.microservice.videogames.cross_cutting.cqrs.IPublisher;
import my.microservice.videogames.cross_cutting.exceptions.EntityNotFoundException;
import my.microservice.videogames.cross_cutting.utils.RestPatchUtils;
import my.microservice.videogames.domain.models.Developer;
import my.microservice.videogames.domain.models.Game;
import my.microservice.videogames.infrastructure.mappers.IDeveloperMapper;
import my.microservice.videogames.infrastructure.repositories.commands.IDeveloperCommandRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class DeveloperCommandService implements IDeveloperCommandService {
    private final IDeveloperCommandRepository repository;
    private final IPublisher publisher;

    private final IDeveloperMapper mapper;

    public DeveloperCommandService(IDeveloperCommandRepository repository, IPublisher publisher, IDeveloperMapper mapper) {
        this.repository = repository;
        this.publisher = publisher;
        this.mapper = mapper;
    }

    @Override
    public List<DeveloperCommand> get() {
        List<Developer> games = (List<Developer>) repository.findAll();
        return mapper.toCommand(games);
    }

    @Override
    public DeveloperCommand getById(Long id) throws EntityNotFoundException {
        Optional<Developer> developer = repository.findById(id);
        return developer.map(mapper::toCommand)
                .orElseThrow(() -> new EntityNotFoundException(Developer.class, id));
    }

    @Override
    public void create(DeveloperCommand command) {
        Developer entity = mapper.toEntity(command);
        repository.save(entity);

        publisher.send(new DeveloperCreatedEvent(entity));
    }

    @Override
    public void delete(Long id) throws EntityNotFoundException {
        if (!repository.existsById(id)) throw new EntityNotFoundException(Developer.class, id);
        repository.deleteById(id);
        publisher.send(new DeveloperDeletedEvent(id));
    }

    @Override
    public void patch(Long id, JsonPatch patch) throws EntityNotFoundException {
        Developer entity = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(Game.class, id));
        DeveloperCommand patched = RestPatchUtils.apply(patch, mapper.toCommand(entity));
        mapper.patch(patched, entity);
        repository.save(entity);

        publisher.send(new DeveloperUpdatedEvent(entity));
    }
}
