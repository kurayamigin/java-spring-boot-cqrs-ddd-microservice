package my.microservice.videogames.application.commands.services;

import com.github.fge.jsonpatch.JsonPatch;
import my.microservice.videogames.application.commands.dtos.SeriesCommand;
import my.microservice.videogames.application.commands.events.series.SeriesCreatedEvent;
import my.microservice.videogames.application.commands.events.series.SeriesDeletedEvent;
import my.microservice.videogames.application.commands.events.series.SeriesUpdatedEvent;
import my.microservice.videogames.application.commands.services.abstractions.ISeriesCommandService;
import my.microservice.videogames.cross_cutting.cqrs.IPublisher;
import my.microservice.videogames.cross_cutting.exceptions.EntityNotFoundException;
import my.microservice.videogames.cross_cutting.utils.ApplicationContextProvider;
import my.microservice.videogames.cross_cutting.utils.RestPatchUtils;
import my.microservice.videogames.domain.models.Serie;
import my.microservice.videogames.infrastructure.mappers.ISeriesMapper;
import my.microservice.videogames.infrastructure.repositories.commands.ISeriesCommandRepository;
import my.microservice.videogames.infrastructure.repositories.queries.ISeriesQueryRepository;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class SeriesCommandService implements ISeriesCommandService {
    private final ISeriesCommandRepository repository;
    private final ISeriesMapper mapper;
    private final IPublisher publisher;

    public SeriesCommandService(ISeriesCommandRepository repository, ISeriesMapper mapper, IPublisher publisher) {
        this.repository = repository;
        this.mapper = mapper;
        this.publisher = publisher;
    }

    @Override
    public List<SeriesCommand> get() {
        List<Serie> entities = (List<Serie>) repository.findAll();
        return mapper.toCommand(entities);
    }

    @Override
    public SeriesCommand getById(Long id) throws EntityNotFoundException {
        Optional<Serie> entity = repository.findById(id);
        return entity.map(mapper::toCommand)
                .orElseThrow(() -> new EntityNotFoundException(Serie.class, id));
    }

    @Override
    public void create(SeriesCommand command) {
        Serie entity = mapper.toEntity(command);
        repository.save(entity);
        publisher.send(new SeriesCreatedEvent(entity));
    }

    @Override
    public void delete(Long id) throws EntityNotFoundException {
        if (!repository.existsById(id)) throw new EntityNotFoundException(Serie.class, id);
        repository.deleteById(id);
        publisher.send(new SeriesDeletedEvent(id));
    }

    @Override
    public void patch(Long id, JsonPatch patch) throws EntityNotFoundException {
        Serie entity = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(Serie.class, id));
        SeriesCommand patched = RestPatchUtils.apply(patch, mapper.toCommand(entity));
        mapper.patch(patched, entity);
        repository.save(entity);
        publisher.send(new SeriesUpdatedEvent(entity));
    }
}
