package my.microservice.videogames.application.commands.services;

import com.github.fge.jsonpatch.JsonPatch;
import my.microservice.videogames.application.commands.dtos.CompanyCommand;
import my.microservice.videogames.application.commands.dtos.PublisherCommand;
import my.microservice.videogames.application.commands.events.game.GameCreatedEvent;
import my.microservice.videogames.application.commands.events.publisher.PublisherCreatedEvent;
import my.microservice.videogames.application.commands.events.publisher.PublisherDeletedEvent;
import my.microservice.videogames.application.commands.events.publisher.PublisherUpdatedEvent;
import my.microservice.videogames.application.commands.services.abstractions.IPublisherCommandService;
import my.microservice.videogames.cross_cutting.cqrs.IPublisher;
import my.microservice.videogames.cross_cutting.exceptions.EntityNotFoundException;
import my.microservice.videogames.cross_cutting.utils.ApplicationContextProvider;
import my.microservice.videogames.cross_cutting.utils.RestPatchUtils;
import my.microservice.videogames.domain.models.Company;
import my.microservice.videogames.domain.models.Publisher;
import my.microservice.videogames.infrastructure.mappers.IPublisherMapper;
import my.microservice.videogames.infrastructure.repositories.commands.IPublisherCommandRepository;
import my.microservice.videogames.infrastructure.repositories.queries.ICompanyQueryRepository;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PublisherCommandService implements IPublisherCommandService {
    private final IPublisherCommandRepository repository;
    private final IPublisherMapper mapper;
    private final IPublisher publisher;

    public PublisherCommandService(IPublisherCommandRepository repository, IPublisherMapper mapper, IPublisher publisher) {
        this.repository = repository;
        this.mapper = mapper;
        this.publisher = publisher;
    }

    @Override
    public List<PublisherCommand> get() {
        List<Publisher> entities = (List<Publisher>) repository.findAll();
        return mapper.toCommand(entities);
    }

    @Override
    public PublisherCommand getById(Long id) throws EntityNotFoundException {
        Optional<Publisher> publisher = repository.findById(id);
        return publisher.map(mapper::toCommand)
                .orElseThrow(() -> new EntityNotFoundException(Publisher.class, id));
    }

    @Override
    public void create(PublisherCommand command) {
        Publisher entity = mapper.toEntity(command);
        repository.save(entity);
        publisher.send(new PublisherCreatedEvent(entity));
     }

    @Override
    public void patch(Long id, JsonPatch patch) throws EntityNotFoundException {
        Publisher entity = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(Publisher.class, id));
        PublisherCommand patched = RestPatchUtils.apply(patch, mapper.toCommand(entity));
        mapper.patch(patched, entity);
        repository.save(entity);

        publisher.send(new PublisherUpdatedEvent(entity));
    }

    @Override
    public void delete(Long id) throws EntityNotFoundException {
        if (!repository.existsById(id)) throw new EntityNotFoundException(Publisher.class, id);
        repository.deleteById(id);
        publisher.send(new PublisherDeletedEvent(id));
    }

}
