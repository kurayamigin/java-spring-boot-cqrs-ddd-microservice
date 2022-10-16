package my.microservice.videogames.application.commands.services;

import com.github.fge.jsonpatch.JsonPatch;
import my.artifacts.abstractions.IPublisher;
import my.microservice.videogames.application.commands.dtos.PublisherCommand;
import my.microservice.videogames.application.commands.events.publisher.PublisherCreatedEvent;
import my.microservice.videogames.application.commands.events.publisher.PublisherDeletedEvent;
import my.microservice.videogames.application.commands.events.publisher.PublisherUpdatedEvent;
import my.microservice.videogames.application.commands.publishers.PublisherPublisher;
import my.microservice.videogames.application.commands.services.abstractions.IPublisherCommandService;
import my.microservice.videogames.cross_cutting.exceptions.EntityNotFoundException;
import my.microservice.videogames.cross_cutting.utils.RestPatchUtils;
import my.microservice.videogames.domain.models.Publisher;
import my.microservice.videogames.infrastructure.mappers.IPublisherMapper;
import my.microservice.videogames.infrastructure.aggregators.abstractions.IPublisherService;
import org.springframework.stereotype.Service;

@Service
public class PublisherCommandService implements IPublisherCommandService {
    private final IPublisherService service;
    private final IPublisherMapper mapper;
    private final IPublisher publisher;

    public PublisherCommandService(IPublisherService service, IPublisherMapper mapper, PublisherPublisher publisher) {
        this.service = service;
        this.mapper = mapper;
        this.publisher = publisher;
    }

    @Override
    public Long create(PublisherCommand command) {
        Publisher entity = mapper.toEntity(command);
        service.create(entity);
        publisher.send(new PublisherCreatedEvent(entity));
        return entity.getId();
    }

    @Override
    public void patch(Long id, JsonPatch patch) throws EntityNotFoundException {
        Publisher entity = service.get(id);
        PublisherCommand patched = RestPatchUtils.apply(patch, mapper.toCommand(entity));
        mapper.patch(patched, entity);
        service.update(entity);
        publisher.send(new PublisherUpdatedEvent(entity));
    }

    @Override
    public void delete(Long id) throws EntityNotFoundException {
        service.delete(id);
        publisher.send(new PublisherDeletedEvent(id));
    }

}
