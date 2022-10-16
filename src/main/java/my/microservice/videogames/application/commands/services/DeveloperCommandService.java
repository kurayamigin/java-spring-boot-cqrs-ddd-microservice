package my.microservice.videogames.application.commands.services;

import com.github.fge.jsonpatch.JsonPatch;
import my.artifacts.abstractions.IPublisher;
import my.microservice.videogames.application.commands.dtos.DeveloperCommand;
import my.microservice.videogames.application.commands.events.developer.DeveloperCreatedEvent;
import my.microservice.videogames.application.commands.events.developer.DeveloperDeletedEvent;
import my.microservice.videogames.application.commands.events.developer.DeveloperUpdatedEvent;
import my.microservice.videogames.application.commands.publishers.DeveloperPublisher;
import my.microservice.videogames.application.commands.services.abstractions.IDeveloperCommandService;
import my.microservice.videogames.cross_cutting.exceptions.EntityNotFoundException;
import my.microservice.videogames.cross_cutting.utils.RestPatchUtils;
import my.microservice.videogames.domain.models.Developer;
import my.microservice.videogames.infrastructure.mappers.IDeveloperMapper;
import my.microservice.videogames.infrastructure.aggregators.abstractions.IDeveloperService;
import org.springframework.stereotype.Service;

@Service
public class DeveloperCommandService implements IDeveloperCommandService {
    private final IDeveloperService service;
    private final IPublisher publisher;

    private final IDeveloperMapper mapper;

    public DeveloperCommandService(IDeveloperService service, DeveloperPublisher publisher, IDeveloperMapper mapper) {
        this.service = service;
        this.publisher = publisher;
        this.mapper = mapper;
    }

    @Override
    public Long create(DeveloperCommand command) {
        Developer entity = mapper.toEntity(command);
        service.create(entity);
        publisher.send(new DeveloperCreatedEvent(entity));
        return entity.getId();
    }

    @Override
    public void delete(Long id) throws EntityNotFoundException {
        service.delete(id);
        publisher.send(new DeveloperDeletedEvent(id));
    }

    @Override
    public void patch(Long id, JsonPatch patch) throws EntityNotFoundException {
        Developer entity = service.get(id);
        DeveloperCommand patched = RestPatchUtils.apply(patch, mapper.toCommand(entity));
        mapper.patch(patched, entity);
        service.update(entity);
        publisher.send(new DeveloperUpdatedEvent(entity));
    }
}
